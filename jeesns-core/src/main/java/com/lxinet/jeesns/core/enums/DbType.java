package com.lxinet.jeesns.core.enums;

public enum DbType {
    MYSQL("com.mysql.jdbc.Driver"), SQL_SERVER("com.microsoft.sqlserver.jdbc.SQLServerDriver"), ORACLE("oracle.jdbc.driver.OracleDriver");

    private String value;

    private DbType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
