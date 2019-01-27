package com.cyssxt.smsspringbootstarter.service;

import com.cyssxt.smsspringbootstarter.dao.SmsDataSource;
import com.cyssxt.smsspringbootstarter.core.SmsSendListener;
import com.cyssxt.smsspringbootstarter.core.SmsSender;
import com.cyssxt.smsspringbootstarter.request.SendReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsSendThread extends Thread {

    private final static Logger logger = LoggerFactory.getLogger(SmsSendThread.class);

    private SmsDataSource[] smsDataSources;
    private SmsSender smsSender;
    private SmsSendListener smsSendListener;

    public SmsSendThread(SmsSender sender,SmsSendListener smsSendListener,SmsDataSource ...smsDataSource){
        this.smsSender = sender;
        this.smsSendListener = smsSendListener;
        this.smsDataSources = smsDataSource;
    }

    boolean pop(){
        boolean hasFlag = false;
        for(SmsDataSource smsDataSource:smsDataSources){
            SendReq req = smsDataSource.pop();
            if(req!=null){
                hasFlag = true;
                logger.info("start to send phone={},msg={}",req.getPhoneNumber(),req.getMsgCode());
                boolean flag = smsSender.send(req);
                if(flag){
                    this.smsSendListener.success(req);
                }else {
                    this.smsSendListener.fail(req);
                }
            }
        }
        return hasFlag;
    }

    @Override
    public void run() {
        for(SmsDataSource smsDataSource:this.smsDataSources) {
            smsDataSource.clear();
        }
        while(true){
            boolean flag = pop();
            if(!flag){
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
