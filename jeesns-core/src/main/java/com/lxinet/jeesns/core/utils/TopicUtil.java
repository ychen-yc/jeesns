package com.lxinet.jeesns.core.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TopicUtil {
    public static String getTopicName(String content) {
        String regex = "#(\\S+)#";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(content);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    public static String formatTopic(String content) {
        try {
            String topicName = getTopicName(content);
            if (StringUtils.isNotBlank(topicName)) {
                return content.replace("#" + topicName + "#", "<a href='" + Const.PROJECT_PATH + "/weibo/topic/" + URLEncoder.encode(topicName, "utf-8") + "' target='_blank'>#" + topicName + "#</a>");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return content;
    }
}
