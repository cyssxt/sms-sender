package com.cyssxt.smsspringbootstarter.core;

import com.cyssxt.smsspringbootstarter.request.SendReq;

public  interface SmsSender {

    /**
     * 实际短信发送接口调用
     * @param req
     * @return
     */
    boolean send(SendReq req);

}
