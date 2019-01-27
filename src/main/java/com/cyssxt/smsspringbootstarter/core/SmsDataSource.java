package com.cyssxt.smsspringbootstarter.core;

import com.cyssxt.smsspringbootstarter.request.SendReq;

public interface SmsDataSource {

    String getMsgCode(String phoneNumber);

    SendReq pop();

    boolean push(SendReq req);

    boolean clear();


}
