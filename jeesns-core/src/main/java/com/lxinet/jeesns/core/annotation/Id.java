package com.lxinet.jeesns.core.annotation;

import com.lxinet.jeesns.core.enums.IdType;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Id
{
  String value();
  
  IdType type() default IdType.NONE;
}
