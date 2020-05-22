package com.cyssxt.smsspringbootstarter.service;

import com.cyssxt.common.exception.ValidException;
import com.cyssxt.common.util.CommonUtil;
import com.cyssxt.smsspringbootstarter.config.SmsSenderConfig;
import com.cyssxt.smsspringbootstarter.constant.RedisKeyConstant;
import com.cyssxt.smsspringbootstarter.constant.SendStatusConstant;
import com.cyssxt.smsspringbootstarter.constant.SmsCode;
import com.cyssxt.smsspringbootstarter.dao.SmsDataSource;
import com.cyssxt.smsspringbootstarter.dao.SmsRepository;
import com.cyssxt.smsspringbootstarter.entity.MsgCodesEntity;
import com.cyssxt.smsspringbootstarter.request.SendReq;
import com.cyssxt.smsspringbootstarter.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class SmsService {

    private final Logger logger = LoggerFactory.getLogger(SmsService.class);

    @Resource
    SmsDataSource smsDataSource;

    @Resource
    SmsSenderConfig smsSenderConfig;

    @Resource
    SmsRepository smsRepository;

    public void sendSms(String phoneNumber) throws ValidException {
        String msgCode = smsSenderConfig.getIsNumber()? CommonUtils.getMsgCodeOfInt():CommonUtils.getMsgCode();
        sendSms(new SendReq(phoneNumber,msgCode));
    }

    public void sendSms(SendReq req) throws ValidException {
        String msgCode = req.getMsgCode();
        String phoneNumber = req.getPhoneNumber();
        //不让验证码短时间重复发送\
        String repeatKey = getRepeatKey(phoneNumber);
        String value = smsDataSource.getValue(repeatKey);
        if(!StringUtils.isEmpty(value)){
            throw new ValidException(SmsCode.SMS_SEND_TOO_FAST);
        }
        MsgCodesEntity msgCodesEntity = new MsgCodesEntity();
        Timestamp now = new Timestamp(new Date().getTime());
        msgCodesEntity.setCreateTime(now);
        msgCodesEntity.setUpdateTime(now);
        msgCodesEntity.setPhoneNumber(phoneNumber);
        msgCodesEntity.setMsgCode(msgCode);
        msgCodesEntity.setStatus(SendStatusConstant.WAIT);
        smsRepository.save(msgCodesEntity);
        String key = getKey(phoneNumber);
        String smsId = msgCodesEntity.getRowId();
        req.setSmsId(smsId);
        smsDataSource.push(RedisKeyConstant.SMS_SET,req);
        smsDataSource.cache(key,msgCode,5,TimeUnit.MINUTES);
        smsDataSource.repeatSet(repeatKey,"1",1, TimeUnit.MINUTES);
    }

    public String getKey(String phoneNumber){
        return String.format("%s_%s",smsSenderConfig.getPrefix(),phoneNumber);
    }
    public String getRepeatKey(String phoneNumber){
        return String.format("%s_%s",smsSenderConfig.getRepeatKey(),phoneNumber);
    }


    public String getValue(String phoneNumber)  {
        String key = getKey(phoneNumber);
        String code = smsDataSource.getValue(key);
        return code;
    }

    /**
     * 校验phone
     * @param phone
     * @param msgCode
     * @return
     */
    public boolean validCode(String phone,String msgCode){
        String code = getValue(phone);
        if(msgCode.equals(code) || ("000000".equals(msgCode) && smsSenderConfig.isTest())){
            return true;
        }
        return false;
    }

    public void updateSmsStatus(String smsId,Byte status){
        if(smsId!=null){
            Optional<MsgCodesEntity> optional = smsRepository.findById(smsId);
            if(optional.isPresent()){
                MsgCodesEntity msgCodesEntity = optional.get();
                msgCodesEntity.setStatus(status);
                msgCodesEntity.setUpdateTime(CommonUtil.getCurrentTimestamp());
                smsRepository.save(msgCodesEntity);
            }
        }
    }

    public void clearSendHistory(){
        SendReq req = null;
        while((req=smsDataSource.pop(RedisKeyConstant.SMS_SET))!=null){
            String phoneNumber = req.getPhoneNumber();
            logger.info("del send history={}",phoneNumber);
            String msgCode = req.getMsgCode();
            String repeatKey = getRepeatKey(phoneNumber);
            String key = getKey(phoneNumber);
            String smsId = req.getSmsId();
            updateSmsStatus(smsId,SendStatusConstant.DEL);
//            if(smsId!=null){
//                Optional<MsgCodesEntity> optional = smsRepository.findById(smsId);
//                if(optional.isPresent()){
//                    MsgCodesEntity msgCodesEntity = optional.get();
//                    msgCodesEntity.setStatus(SendStatusConstant.DEL);
//                    msgCodesEntity.setUpdateTime(DateUtils.getCurrentTimestamp());
//                    smsRepository.save(msgCodesEntity);
//                }
//            }
            logger.info("del={},smsId={}",phoneNumber,smsId);
            smsDataSource.onDel(key,repeatKey,msgCode);
        }


    }
}
