package com.lxinet.jeesns.core.service.impl;

import com.lxinet.jeesns.core.conditions.SqlWrapper;
import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.service.IBaseService;
import com.lxinet.jeesns.core.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseServiceImpl<T>
        implements IBaseService<T> {
    @Autowired
    private BaseMapper<T> baseMapper;

    public T findById(Integer id) {
        return this.baseMapper.getById(id, getTClass());
    }

    public List<T> listAll() {
        SqlWrapper<T> sqlWrapper = new SqlWrapper(getTClass());
        return this.baseMapper.listAll(sqlWrapper);
    }

    public List<T> listAll(SqlWrapper<T> sqlWrapper) {
        return this.baseMapper.listAll(sqlWrapper);
    }

    public List<T> listByPage() {
        SqlWrapper<T> sqlWrapper = new SqlWrapper(getTClass());
        return this.baseMapper.listByPage(PageUtil.getPage(), sqlWrapper);
    }

    public List<T> listByPage(Page page, SqlWrapper<T> sqlWrapper) {
        return this.baseMapper.listByPage(page, sqlWrapper);
    }

    public boolean save(T t) {
        return this.baseMapper.saveObj(t) > 0;
    }

    public boolean update(T t) {
        return this.baseMapper.updateObj(t) > 0;
    }

    public boolean delete(T t) {
        return this.baseMapper.deleteObj(t) > 0;
    }

    public boolean deleteById(Integer id) {
        return this.baseMapper.deleteById(id, getTClass()) > 0;
    }

    private Class<T> getTClass() {
        Class<T> tClass = (Class) ((java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }
}
