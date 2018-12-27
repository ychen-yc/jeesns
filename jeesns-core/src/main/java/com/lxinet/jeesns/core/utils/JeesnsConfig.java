package com.lxinet.jeesns.core.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("jeesnsConfig")
public class JeesnsConfig {
    @Value("${managePath}")
    private String managePath;
    @Value("${groupPath}")
    private String groupPath;
    @Value("${weiboPath}")
    private String weiboPath;
    @Value("${manageTemplate}")
    private String manageTemplate;
    @Value("${memberTemplate}")
    private String memberTemplate;
    @Value("${frontTemplate}")
    private String frontTemplate;
    @Value("${mobileTemplate}")
    private String mobileTemplate;

    public String getManagePath() {
        return this.managePath;
    }

    public String getGroupPath() {
        return this.groupPath;
    }

    public String getWeiboPath() {
        return this.weiboPath;
    }

    public String getManageTemplate() {
        return this.manageTemplate;
    }

    public String getMemberTemplate() {
        return this.memberTemplate;
    }

    public String getFrontTemplate() {
        return this.frontTemplate;
    }

    public String getMobileTemplate() {
        return this.mobileTemplate;
    }
}
