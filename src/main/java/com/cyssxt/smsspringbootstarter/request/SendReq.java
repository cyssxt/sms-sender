package com.cyssxt.smsspringbootstarter.request;

import lombok.Data;

@Data
public class SendReq {
    private String phoneNumber;
    private String msgCode;
}
