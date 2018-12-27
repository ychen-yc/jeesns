package com.lxinet.jeesns.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static boolean isEmpty(String str) {
        if ((str == null) || ("".equals(str))) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String str) {
        if ((str != null) && (!"".equals(str))) {
            return true;
        }
        return false;
    }

    public static boolean isBlank(String str) {
        if ((str == null) || ("".equals(str.trim()))) {
            return true;
        }
        return false;
    }

    public static boolean isNotBlank(String str) {
        if ((str != null) && (!"".equals(str.trim()))) {
            return true;
        }
        return false;
    }

    public static boolean isNumber(String str) {
        String regex = "^[0-9]+\\.{0,1}[0-9]{0,2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isInteger(String str) {
        String regex = "^[0-9]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean atLeast(String str, int num) {
        String regex = "^\\d{" + num + ",}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isEmail(String str) {
        String regex = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isLetterOrDigit(String str) {
        String regex = "^[A-Za-z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isLetter(String str) {
        String regex = "^[A-Za-z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean checkNickname(String str) {
        String regex = "^[a-zA-Z0-9_\\-\\u4e00-\\u9fa5]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}