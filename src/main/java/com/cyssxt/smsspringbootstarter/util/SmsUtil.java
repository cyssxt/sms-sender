package com.cyssxt.smsspringbootstarter.util;

import com.cyssxt.smsspringbootstarter.constant.SendStatusConstant;
import com.cyssxt.smsspringbootstarter.core.SmsSender;
import com.cyssxt.smsspringbootstarter.dao.SmsRepository;
import com.cyssxt.smsspringbootstarter.entity.MsgCodesEntity;
import com.cyssxt.smsspringbootstarter.request.SendReq;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

@Component
public class SmsUtil {

    @Resource
    SmsRepository smsRepository;

    @Resource
    SmsSender smsSender;

    public void sender(SendReq req){
       
    }
}
