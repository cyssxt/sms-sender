package com.cyssxt.smsspringbootstarter.util;

import java.util.Random;

public class CommonUtils {
    private final static int CODE_LENGTH = 6;
    public static String getMsgCodeOfInt() {
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<CODE_LENGTH;i++){
            Random random = new Random(10);
            sb.append(random.nextInt());
        }
        return sb.toString();
    }

    public static String getMsgCode() {
        return null;
    }
}
