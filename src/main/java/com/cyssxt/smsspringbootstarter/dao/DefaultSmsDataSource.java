package com.cyssxt.smsspringbootstarter.dao;

import com.cyssxt.smsspringbootstarter.request.SendReq;

public class DefaultSmsDataSource implements SmsDataSource {

    @Override
    public String getMsgCode(String phoneNumber) {
        return null;
    }

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
