package com.cyssxt.smsspringbootstarter.core;

import com.cyssxt.smsspringbootstarter.request.SendReq;

public  interface SmsSender {

    boolean send(SendReq req);
}
