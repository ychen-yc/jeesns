package com.lxinet.jeesns.core.annotation;

import com.lxinet.jeesns.core.interceptor.JeesnsInterceptor;

import java.lang.annotation.*;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.METHOD})
public @interface After
{
  Class<? extends JeesnsInterceptor> value();
}
