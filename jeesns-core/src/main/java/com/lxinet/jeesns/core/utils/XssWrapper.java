package com.lxinet.jeesns.core.utils;

import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XssWrapper
        extends HttpServletRequestWrapper {
    private static final String REGEX_SCRIPT = "<script[\\s\\S]*?<\\/script>";
    private static final String REGEX_STYLE = "<style[^>]*?>[\\s\\S]*?<\\/style>";

    public XssWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }

    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }
        return cleanXSS(value);
    }

    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null) {
            return null;
        }
        return cleanXSS(value);
    }

    private String cleanXSS(String value) {
        value = dealScript(value);
        value = dealStyle(value);

        String[] eventKeywords = {"onmouseover", "onmouseout", "onmousedown", "onmouseup", "onmousemove", "onclick", "ondblclick", "onkeypress", "onkeydown", "onkeyup", "ondragstart", "onerrorupdate", "onhelp", "onreadystatechange", "onrowenter", "onrowexit", "onselectstart", "onload", "onunload", "onbeforeunload", "onblur", "onerror", "onfocus", "onresize", "onscroll", "oncontextmenu", "alert"};
        for (int i = 0; i < eventKeywords.length; i++) {
            value = value.replaceAll("(?i)" + eventKeywords[i], "_" + eventKeywords[i]);
        }
        return value;
    }

    private static String dealScript(String val) {
        Pattern p = Pattern.compile("<script[\\s\\S]*?<\\/script>");
        return htmlEscape(p, val);
    }

    private static String dealStyle(String val) {
        Pattern p = Pattern.compile("<style[^>]*?>[\\s\\S]*?<\\/style>");
        return htmlEscape(p, val);
    }

    private static String htmlEscape(Pattern p, String val) {
        Matcher m = p.matcher(val);
        while (m.find()) {
            String s = m.group();
            String newVal = HtmlUtils.htmlEscape(s);
            val = val.replace(s, newVal);
        }
        return val;
    }
}
