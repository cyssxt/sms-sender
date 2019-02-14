package com.cyssxt.smsspringbootstarter.service;

import com.cyssxt.smsspringbootstarter.config.SmsSenderConfig;
import com.cyssxt.smsspringbootstarter.constant.RedisKeyConstant;
import com.cyssxt.smsspringbootstarter.constant.SendStatusConstant;
import com.cyssxt.smsspringbootstarter.dao.SmsDataSource;
import com.cyssxt.smsspringbootstarter.dao.SmsRepository;
import com.cyssxt.smsspringbootstarter.entity.MsgCodesEntity;
import com.cyssxt.smsspringbootstarter.request.SendReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class SmsService {

    @Resource
    SmsDataSource smsDataSource;

    @Resource
    SmsSenderConfig smsSenderConfig;

    @Resource
    SmsRepository smsRepository;

    public void sendSms(SendReq req){
        String msgCode = req.getMsgCode();
        String phoneNumber = req.getPhoneNumber();
        MsgCodesEntity msgCodesEntity = new MsgCodesEntity();
        Timestamp now = new Timestamp(new Date().getTime());
        msgCodesEntity.setCreateTime(now);
        msgCodesEntity.setUpdateTime(now);
        msgCodesEntity.setPhoneNumber(phoneNumber);
        msgCodesEntity.setMsgCode(msgCode);
        msgCodesEntity.setStatus(SendStatusConstant.WAIT);
        smsRepository.save(msgCodesEntity);
        String key = getKey(phoneNumber);
        smsDataSource.push(RedisKeyConstant.SMS_SET,req);
        smsDataSource.cache(key,msgCode);
    }

    public String getKey(String phoneNumber){
        return String.format("%s_%s",smsSenderConfig.getPrefix(),phoneNumber);
    }


    public String getMsgCode(String phoneNumber)  {
        String key = getKey(phoneNumber);
        String code = smsDataSource.getMsgCode(key);
        return code;
    }

    /**
     * 校验phone
     * @param phone
     * @param msgCode
     * @return
     */
    public boolean validCode(String phone,String msgCode){
        String code = getMsgCode(phone);
        if(msgCode.equals(code) || ("000000".equals(code) && smsSenderConfig.isTest())){
            return true;
        }
        return false;
    }
}
