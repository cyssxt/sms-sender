package com.cyssxt.smsspringbootstarter.service;

import com.cyssxt.smsspringbootstarter.config.SmsSenderConfig;
import com.cyssxt.smsspringbootstarter.core.SmsDataSource;
import com.cyssxt.smsspringbootstarter.request.SendReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SmsService {

    @Resource
    SmsDataSource smsDataSource;

    @Resource
    SmsSenderConfig smsSenderConfig;

    public void sendSms(SendReq req){
        smsDataSource.push(req);
    }


    public String getMsgCode(String phoneNumber)  {
        String code = smsDataSource.getMsgCode(phoneNumber);
        return code;
    }

    public boolean validCode(String phone,String msgCode){
        String code = getMsgCode(phone);
        if(msgCode.equals(code) || ("000000".equals(code) && smsSenderConfig.isTest())){
            return true;
        }
        return false;
    }
}
