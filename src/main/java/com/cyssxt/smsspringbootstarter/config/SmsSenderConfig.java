package com.cyssxt.smsspringbootstarter.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class SmsSenderConfig {
    String type;
    int timeout = 0;
    boolean autogenerator = true;
    String redisHost;
    @Value("${cyssxt.sms.sender.threadNum:2}")
    int threadNum=2;
    @Value("${cyssxt.sms.test:false}")
    boolean test = false;
    @Value("${cyssxt.sms.prefix:msgcode_}")
    String prefix;
    @Value("${cyssxt.sms.prefix:msgcode_repeat_}")
    String repeatKey;
}
