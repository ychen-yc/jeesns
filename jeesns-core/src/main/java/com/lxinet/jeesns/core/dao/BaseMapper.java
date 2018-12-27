package com.lxinet.jeesns.core.dao;

import com.lxinet.jeesns.core.conditions.SqlWrapper;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.provider.SqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public abstract interface BaseMapper<T>
{
  @InsertProvider(type=SqlProvider.class, method="saveObj")
  @Options(useGeneratedKeys=true, keyProperty="id")
  public abstract int saveObj(T paramT);
  
  @SelectProvider(type=SqlProvider.class, method="listAll")
  public abstract List<T> listAll(SqlWrapper<T> paramSqlWrapper);
  
  @SelectProvider(type=SqlProvider.class, method="listByPage")
  public abstract List<T> listByPage(@Param("page") Page paramPage, @Param("sqlWrapper") SqlWrapper<T> paramSqlWrapper);
  
  @UpdateProvider(type=SqlProvider.class, method="updateObj")
  public abstract int updateObj(T paramT);
  
  @SelectProvider(type=SqlProvider.class, method="getById")
  public abstract T getById(@Param("id") Integer paramInteger, @Param("c") Class<?> paramClass);
  
  @DeleteProvider(type=SqlProvider.class, method="deleteById")
  public abstract int deleteById(@Param("id") Integer paramInteger, @Param("c") Class<?> paramClass);
  
  @DeleteProvider(type=SqlProvider.class, method="deleteObj")
  public abstract int deleteObj(T paramT);
}
