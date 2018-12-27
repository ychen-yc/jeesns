package com.lxinet.jeesns.core.conditions;

public class SqlWrapper<T> {
    public static final String DESC = " desc";
    public static final String ASC = " asc";
    private String orderBy;
    private T entity;
    private Class<?> entityClass;

    public SqlWrapper() {
    }

    public SqlWrapper(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public SqlWrapper(T entity) {
        setEntity(entity);
    }

    public void setEntity(T entity) {
        this.entity = entity;
        this.entityClass = entity.getClass();
    }

    public SqlWrapper order(String columns) {
        this.orderBy = (columns + " asc");
        return this;
    }

    public SqlWrapper order(String columns, String orderBy) {
        this.orderBy = (columns + " " + orderBy);
        return this;
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    public Class<?> getEntityClass() {
        return this.entityClass;
    }
}
