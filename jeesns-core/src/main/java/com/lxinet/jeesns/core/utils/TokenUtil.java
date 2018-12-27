package com.lxinet.jeesns.core.utils;

import java.util.UUID;

public class TokenUtil {
    public static String getToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
