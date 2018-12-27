package com.lxinet.jeesns.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
    private static final String DATETIME_SDF = "yyyy-MM-dd HH:mm:ss";

    public static Date formatDateTime(String datetimeStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(datetimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
