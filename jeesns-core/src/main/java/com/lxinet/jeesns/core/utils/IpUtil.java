package com.lxinet.jeesns.core.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if ((!StringUtils.isEmpty(ip)) && (!"unknown".equalsIgnoreCase(ip))) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if ((!StringUtils.isEmpty(ip)) && (!"unknown".equalsIgnoreCase(ip))) {
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            }
            return ip;
        }
        return request.getRemoteAddr();
    }
}
