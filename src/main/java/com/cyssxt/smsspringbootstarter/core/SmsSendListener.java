package com.cyssxt.smsspringbootstarter.core;

import com.cyssxt.smsspringbootstarter.request.SendReq;

public interface SmsSendListener {

    void success(SendReq req);

    void fail(SendReq req);
}
