package com.cyssxt.smsspringbootstarter.dao;

import com.cyssxt.smsspringbootstarter.request.SendReq;

public class RedisDataSource implements SmsDataSource{

    @Override
    public String getMsgCode(String phoneNumber) {
        return null;
    }

    @Override
    public SendReq pop() {
        return null;
    }

    @Override
    public boolean push(SendReq req) {
        return false;
    }

    @Override
    public boolean clear() {
        return false;
    }

}
