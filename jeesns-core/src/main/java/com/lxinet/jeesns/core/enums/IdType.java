package com.lxinet.jeesns.core.enums;

public enum IdType {
    AUTO(0, "数据库ID自增"), INPUT(1, "用户输入ID"), UUID(2, "UUID"), NONE(3, "不设置");

    private final int key;
    private final String desc;

    private IdType(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public static IdType getIdType(int idType) {
        IdType[] its = values();
        for (int i = 0; i < its.length; i++) {
            IdType it = its[i];
            if (it.getKey() == idType) {
                return it;
            }
        }
        return AUTO;
    }

    public int getKey() {
        return this.key;
    }

    public String getDesc() {
        return this.desc;
    }
}
