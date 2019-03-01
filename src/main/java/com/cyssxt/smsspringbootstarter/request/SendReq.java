package com.cyssxt.smsspringbootstarter.request;

import lombok.Data;

@Data
public class SendReq {
    private String phoneNumber;
    private String msgCode;
    Integer smsId;

    public SendReq(String phoneNumber, String msgCode) {
        this.phoneNumber = phoneNumber;
        this.msgCode = msgCode;
    }


}
