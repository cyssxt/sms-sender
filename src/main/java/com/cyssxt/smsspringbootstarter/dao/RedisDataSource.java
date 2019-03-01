package com.cyssxt.smsspringbootstarter.dao;

import com.cyssxt.smsspringbootstarter.request.SendReq;

import java.util.concurrent.TimeUnit;

public class RedisDataSource implements SmsDataSource{

    @Override
    public String getValue(String phoneNumber) {
        return null;
    }

    @Override
    public SendReq pop(String key) {
        return null;
    }

    @Override
    public boolean push(String key, SendReq req) {
        return false;
    }

    @Override
    public boolean cache(String key, String msgCode,int unitValue,TimeUnit timeUnit) {
        return false;
    }

    @Override
    public boolean clear(String key) {
        return false;
    }

    @Override
    public void repeatSet(String repeatKey, String value, int time, TimeUnit timeUint) {

    }

    @Override
    public void onDel(String key, String repeatKey, String msgCode) {

    }

}
