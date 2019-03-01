package com.cyssxt.smsspringbootstarter.core;

import com.cyssxt.smsspringbootstarter.constant.RedisKeyConstant;
import com.cyssxt.smsspringbootstarter.dao.SmsDataSource;
import com.cyssxt.smsspringbootstarter.service.SmsSendThread;
import com.cyssxt.smsspringbootstarter.service.SmsService;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class SmsManager {

    @Resource
    SmsDataSource smsDataSource;

    @Resource
    SmsSender smsSender;

    @Resource
    SmsSendListener smsSendListener;

    @Resource
    SmsService smsService;

    private ExecutorService executorService;

    @PostConstruct
    public void init(){
        int threadNum = 10;
        smsService.clearSendHistory();
        executorService  = Executors.newFixedThreadPool(threadNum);
        //清空短信息
        smsDataSource.clear(RedisKeyConstant.SMS_SET);
        for(int i=0;i<threadNum;i++) {
            executorService.execute(new SmsSendThread(smsSender,smsSendListener,smsDataSource));
        }
    }
}
