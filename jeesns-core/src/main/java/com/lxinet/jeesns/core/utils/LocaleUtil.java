package com.lxinet.jeesns.core.utils;

import org.springframework.context.MessageSource;

import java.util.Locale;

public class LocaleUtil {
    private static final ThreadLocal<Locale> tlLocale = new ThreadLocal() {
        protected Locale initialValue() {
            return Locale.SIMPLIFIED_CHINESE;
        }
    };
    private static MessageSource messageSource;

    public static Locale getLocale() {
        return (Locale) tlLocale.get();
    }

    public static void setMessageSource(MessageSource messageSource) {
        LocaleUtil.messageSource = messageSource;
    }

    public static MessageSource getMessageSource() {
        return messageSource;
    }
}
