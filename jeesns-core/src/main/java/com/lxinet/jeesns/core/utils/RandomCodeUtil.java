package com.lxinet.jeesns.core.utils;

import java.util.UUID;

public class RandomCodeUtil {
    public static String randomCode6() {
        int code = (int) ((Math.random() * 9.0D + 1.0D) * 100000.0D);
        return String.valueOf(code);
    }

    public static String uuid() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }
}