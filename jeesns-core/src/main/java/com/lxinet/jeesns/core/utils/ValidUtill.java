package com.lxinet.jeesns.core.utils;

import com.lxinet.jeesns.core.enums.Messages;
import com.lxinet.jeesns.core.exception.ParamException;

public class ValidUtill {
    public static void checkParam(Boolean boo, String msg) {
        if (boo.booleanValue()) {
            throw new ParamException(msg);
        }
    }

    public static void checkParam(Boolean... boos) {
        Boolean[] arrayOfBoolean = boos;
        int i = arrayOfBoolean.length;
        for (int j = 0; j < i; j++) {
            boolean boo = arrayOfBoolean[j].booleanValue();
            if (boo) {
                throw new ParamException();
            }
        }
    }

    public static void checkIsNull(Object obj) {
        if (null == obj) {
            throw new ParamException();
        }
    }

    public static void checkIsNull(Object obj, String msg) {
        if (null == obj) {
            throw new ParamException(msg);
        }
    }

    public static void checkIsBlank(String val, String msg) {
        if (StringUtils.isBlank(val)) {
            throw new ParamException(msg);
        }
    }

    public static void checkIsNull(Object obj, Messages messages) {
        if (null == obj) {
            throw new ParamException(messages);
        }
    }

    public static void checkIsBlank(String val, Messages messages) {
        if (StringUtils.isBlank(val)) {
            throw new ParamException(messages);
        }
    }
}
