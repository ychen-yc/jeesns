package com.lxinet.jeesns.core.utils;

import java.util.UUID;

public class UuidUtil {
    public static String getUnid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
