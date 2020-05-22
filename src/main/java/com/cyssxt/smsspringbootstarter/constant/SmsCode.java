package com.cyssxt.smsspringbootstarter.constant;

import com.cyssxt.common.response.ErrorMessage;

public enum SmsCode implements ErrorMessage {
    SMS_SEND_TOO_FAST(3000001, "短信发送过快");

    Integer retCode;
    String msg;

    SmsCode(Integer retCode, String msg) {
        this.retCode = retCode;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return retCode;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public int getStatusCode() {
        return 200;
    }
}
