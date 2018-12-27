package com.lxinet.jeesns.core.invoke;

import com.lxinet.jeesns.core.annotation.Plugin;
import com.lxinet.jeesns.core.exception.JeeException;
import com.lxinet.jeesns.core.exception.NotAuthorizeException;
import com.lxinet.jeesns.core.exception.OpeErrorException;
import com.lxinet.jeesns.core.utils.SpringContextUtil;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.target.SingletonTargetSource;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JeesnsInvoke {
    public static Object invoke(String className, String methodName, Object... params) {
        try {
            Object bean = SpringContextUtil.getBean(className);
            Class clazz = bean.getClass();
            if (clazz.toString().startsWith("class com.sun.proxy")) {
                bean = ((SingletonTargetSource) ((Advised) bean).getTargetSource()).getTarget();
                clazz = bean.getClass();
            }
            validate(clazz);
            Class<?>[] paramsTypes = new Class[params.length];
            for (int i = 0; i < params.length; i++) {
                paramsTypes[i] = params[i].getClass();
            }
            Method method = clazz.getMethod(methodName, paramsTypes);
            return method.invoke(bean, params);
        } catch (NoSuchBeanDefinitionException e1) {
            throw new NotAuthorizeException();
        } catch (NoSuchMethodException e2) {
            throw new NotAuthorizeException();
        } catch (Exception e) {
            e.printStackTrace();
            String msg = e.getMessage();
            if ((e instanceof InvocationTargetException)) {
                msg = ((InvocationTargetException) e).getTargetException().getMessage();
            }
            throw new JeeException(msg);
        }
    }

    private static void validate(Class clazz) {
        Annotation plugin = clazz.getAnnotation(Plugin.class);
        if (null == plugin) {
            throw new OpeErrorException("非法访问");
        }
    }
}
