package com.lxinet.jeesns.core.service;

import com.lxinet.jeesns.core.conditions.SqlWrapper;
import com.lxinet.jeesns.core.model.Page;

import java.util.List;

public abstract interface IBaseService<T> {
    public abstract T findById(Integer paramInteger);

    public abstract List<T> listAll();

    public abstract List<T> listAll(SqlWrapper<T> paramSqlWrapper);

    public abstract List<T> listByPage();

    public abstract List<T> listByPage(Page paramPage, SqlWrapper<T> paramSqlWrapper);

    public abstract boolean save(T paramT);

    public abstract boolean update(T paramT);

    public abstract boolean delete(T paramT);

    public abstract boolean deleteById(Integer paramInteger);
}
