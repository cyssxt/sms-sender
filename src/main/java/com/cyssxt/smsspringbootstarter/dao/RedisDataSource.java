package com.cyssxt.smsspringbootstarter.dao;

import com.cyssxt.smsspringbootstarter.request.SendReq;

public class RedisDataSource implements SmsDataSource{

    @Override
    public String getMsgCode(String phoneNumber) {
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
    public boolean cache(String key, String msgCode) {
        return false;
    }

    @Override
    public boolean clear(String key) {
        return false;
    }

}
