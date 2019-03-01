package com.cyssxt.smsspringbootstarter.dao;


import java.util.concurrent.TimeUnit;

public abstract class AbstractSmsDataSource implements SmsDataSource {

    @Override
    public boolean cache(String key, String msgCode, int unitValue, TimeUnit timeUnit) {
        return true;
    }

    @Override
    public void onDel(String key,String repeatKey, String msgCode) {

    }
}
