package com.lxinet.jeesns.core.provider;

import com.lxinet.jeesns.core.annotation.Column;
import com.lxinet.jeesns.core.annotation.Id;
import com.lxinet.jeesns.core.annotation.Table;
import com.lxinet.jeesns.core.conditions.SqlWrapper;
import com.lxinet.jeesns.core.enums.FillTime;
import com.lxinet.jeesns.core.enums.IdType;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.StringUtils;
import com.lxinet.jeesns.core.utils.UuidUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class SqlProvider<T> {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String LIST_ALL = "listAll";
    public static final String LIST_BY_PAGE = "listByPage";
    public static final String GET_BY_ID = "getById";
    public static final String DELETE_BY_ID = "deleteById";
    public static final String DELETE_OBJ = "deleteObj";
    public static final String SAVE_OBJ = "saveObj";
    public static final String UPDATE_OBJ = "updateObj";
    public static final String FIND_BY_COLUMNS = "findByColumns";

    public String listAll(SqlWrapper<T> sqlWrapper) {
        SQL sql = new SQL();
        ((SQL) sql.SELECT("*")).FROM(getTableName(sqlWrapper.getEntityClass()));
        if (StringUtils.isNotBlank(sqlWrapper.getOrderBy())) {
            sql.ORDER_BY(sqlWrapper.getOrderBy());
        }
        return sql.toString();
    }

    public String listByPage(@Param("page") Page page, @Param("sqlWrapper") SqlWrapper<T> sqlWrapper) {
        SQL sql = new SQL();
        ((SQL) sql.SELECT("*")).FROM(getTableName(sqlWrapper.getEntityClass()));
        if (StringUtils.isNotBlank(sqlWrapper.getOrderBy())) {
            sql.ORDER_BY(sqlWrapper.getOrderBy());
        }
        return sql.toString();
    }

    public String getById(@Param("id") Integer id, @Param("c") Class<?> c) {
        SQL sql = new SQL();
        ((SQL) ((SQL) sql.SELECT("*")).FROM(getTableName(c))).WHERE(getIdName(c) + "=#{id}");
        return sql.toString();
    }

    public String deleteById(@Param("id") Integer id, @Param("c") Class<?> c) {
        SQL sql = new SQL();
        ((SQL) sql.DELETE_FROM(getTableName(c))).WHERE(getIdName(c) + "=#{id}");
        return sql.toString();
    }

    public String deleteObj(T t) {
        SQL sql = new SQL();
        ((SQL) sql.DELETE_FROM(getTableName(t.getClass()))).WHERE(getIdName(t.getClass()) + "=#{" + getIdFieldName(t.getClass()) + "}");
        return sql.toString();
    }

    public String saveObj(T t) {
        SQL sql = new SQL();
        sql.INSERT_INTO(getTableName(t.getClass()));
        Field[] field = t.getClass().getDeclaredFields();
        for (Field nameField : field) {
            Column column = (Column) nameField.getAnnotation(Column.class);
            Id id = (Id) nameField.getAnnotation(Id.class);
            if ((null != column) || (null != id)) {
                String name = nameField.getName();
                Object val = getFieldValue(t, name);
                boolean isFill = false;
                if (null == val) {
                    String fieldType = nameField.getType().toString();
                    if (fieldType.equalsIgnoreCase("class java.util.Date")) {
                        FillTime now = column.currTime();
                        if (FillTime.INSERT == now) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            val = sdf.format(new Date());
                            isFill = true;
                        }
                    }
                }
                if (null != val) {
                    if (null == id) {
                        sql.INTO_COLUMNS(new String[]{column.value()});
                        if (isFill) {
                            sql.INTO_VALUES(new String[]{"'" + String.valueOf(val) + "'"});
                        } else {
                            sql.INTO_VALUES(new String[]{"#{" + name + "}"});
                        }
                    } else if ((id.type() != IdType.AUTO) && (id.type() != IdType.NONE)) {
                        sql.INTO_COLUMNS(new String[]{id.value()});
                        if (id.type() == IdType.UUID) {
                            sql.INTO_VALUES(new String[]{"'" + UuidUtil.getUnid() + "'"});
                        } else {
                            sql.INTO_VALUES(new String[]{"#{" + name + "}"});
                        }
                    }
                }
            }
        }
        return sql.toString();
    }

    public String updateObj(T t) {
        SQL sql = new SQL();
        sql.UPDATE(getTableName(t.getClass()));
        Field[] fields = t.getClass().getDeclaredFields();
        Id id = null;
        String idName = null;
        for (Field field : fields) {
            if (null == id) {
                id = (Id) field.getAnnotation(Id.class);
                if (null != id) {
                    idName = field.getName();
                }
            }
            Column column = (Column) field.getAnnotation(Column.class);
            if (null != column) {
                Object val = getFieldValue(t, field.getName());
                if (null != val) {
                    sql.SET(column.value() + "=#{" + field.getName() + "}");
                }
            }
        }
        sql.WHERE(id.value() + "=#{" + idName + "}");
        return sql.toString();
    }

    public String findByColumns(Map<String, Object> params, Class<?> c) {
        SQL sql = new SQL();
        Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
        int i = 0;
        ((SQL) sql.SELECT("*")).FROM(getTableName(c));
        while (iterator.hasNext()) {
            String column = (String) ((Map.Entry) iterator.next()).getKey();
            if (!column.startsWith("param")) {
                sql.WHERE(column + "=#{" + column + "}");
            }
            i++;
        }
        return sql.toString();
    }

    private String getTableName(Class<?> c) {
        Table annotation = (Table) c.getAnnotation(Table.class);
        String tableName = annotation.value();
        return tableName;
    }

    private String getIdName(Class<?> c) {
        String idName = null;
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            Id annotation = (Id) field.getAnnotation(Id.class);
            if (null != annotation) {
                idName = annotation.value();
                break;
            }
        }
        return idName;
    }

    private String getIdFieldName(Class<?> c) {
        String idFieldName = null;
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            Id annotation = (Id) field.getAnnotation(Id.class);
            if (null != annotation) {
                idFieldName = field.getName();
                break;
            }
        }
        return idFieldName;
    }

    private Object getFieldValue(T t, String field) {
        String firstLetter = field.substring(0, 1).toUpperCase();
        String getMethodName = "get" + firstLetter + field.substring(1);

        Method getMethod = null;
        try {
            getMethod = t.getClass().getMethod(getMethodName, new Class[0]);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Object value = null;
        try {
            value = getMethod.invoke(t, new Object[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return value;
    }
}
