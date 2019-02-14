package com.cyssxt.smsspringbootstarter.dao;

import com.cyssxt.smsspringbootstarter.request.SendReq;

public interface SmsDataSource {

    /**
     * 获取短信验证码
     * @param key
     * @return
     */
    String getMsgCode(String key);

    /**
     * 队列压出短信
     * @return
     */
    SendReq pop(String key);

    /**
     * 将短信发送押入发送队列
     * @return
     */
    boolean push(String key,SendReq req);

    /**
     * 缓存数据
     * @param key
     * @param msgCode
     * @return
     */
    boolean cache(String key,String msgCode);

    /**
     * 清空短信内容
     * @return
     */
    boolean clear(String key);


}
