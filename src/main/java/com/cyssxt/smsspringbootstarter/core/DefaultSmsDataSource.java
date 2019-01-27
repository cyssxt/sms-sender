package com.cyssxt.smsspringbootstarter.core;

import com.cyssxt.smsspringbootstarter.request.SendReq;

import java.util.HashMap;
import java.util.Map;

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
