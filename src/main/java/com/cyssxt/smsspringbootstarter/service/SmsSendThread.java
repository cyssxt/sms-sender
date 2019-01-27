package com.cyssxt.smsspringbootstarter.service;

import com.cyssxt.smsspringbootstarter.core.SmsDataSource;
import com.cyssxt.smsspringbootstarter.core.SmsSendListener;
import com.cyssxt.smsspringbootstarter.core.SmsSender;
import com.cyssxt.smsspringbootstarter.request.SendReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsSendThread extends Thread {

    private final static Logger logger = LoggerFactory.getLogger(SmsSendThread.class);

    private SmsDataSource smsDataSource;
    private SmsSender smsSender;
    private SmsSendListener smsSendListener;

    public SmsSendThread(SmsDataSource smsDataSource, SmsSender sender,SmsSendListener smsSendListener){
        this.smsDataSource = smsDataSource;
        this.smsSender = sender;
        this.smsSendListener = smsSendListener;
    }

    @Override
    public void run() {
        smsDataSource.clear();
        while(true){
            SendReq value = smsDataSource.pop();
            if(null!=value){
                logger.info("start to send phone={},msg={}",value.getPhoneNumber(),value.getMsgCode());
                boolean flag = smsSender.send(value);
                if(flag){
                    this.smsSendListener.success(value);
                }else {
                    this.smsSendListener.fail(value);
                }
            }else{
                //如果队列中没有短信 则休息5s
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
