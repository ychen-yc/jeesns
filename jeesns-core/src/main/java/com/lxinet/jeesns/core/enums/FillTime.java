package com.lxinet.jeesns.core.enums;

public enum FillTime {
    NONE("none", "不处理"), INSERT("insert", "新增"), UPDATE("update", "修改");

    private final String key;
    private final String desc;

    private FillTime(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return this.key;
    }

    public String getDesc() {
        return this.desc;
    }
}
