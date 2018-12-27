package com.lxinet.jeesns.core.aop;

import com.lxinet.jeesns.core.annotation.UsePage;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.PageUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


@Aspect
@Component
public class PageAspectAop {
    @Pointcut("@annotation(com.lxinet.jeesns.core.annotation.UsePage)")
    public void annotationPointCut() {
    }

    @Before("annotationPointCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Method method = sign.getMethod();
        UsePage annotation = (UsePage) method.getAnnotation(UsePage.class);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (annotation != null) {
            Page page = new Page(request);
            PageUtil.setPage(page);
        }
    }

    private class JeePlus {
        private JeePlus() {
        }
    }
}
