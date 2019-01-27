package com.cyssxt.smsspringbootstarter.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@ConfigurationProperties(prefix = "cyssxt.sms")
@PropertySource(value = "classpath:sms.properties")
public class SmsSenderConfig {

    String type;
    int timeout = 0;
    boolean autogenerator = true;
    @Value("${redis.host}")
    String redisHost;
    @Value("${thread-num}")
    int threadNum=2;
    boolean test = false;
}
