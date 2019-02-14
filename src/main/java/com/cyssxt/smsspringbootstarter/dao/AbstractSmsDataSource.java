package com.cyssxt.smsspringbootstarter.dao;


public abstract class AbstractSmsDataSource implements SmsDataSource {

    @Override
    public boolean cache(String key, String msgCode) {
        return true;
    }
}
