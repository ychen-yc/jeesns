package com.lxinet.jeesns.core.exception;

public class NotAuthorizeException
        extends JeeException {
    public NotAuthorizeException() {
        super("您的系统未授权，无法使用此功能，请联系系统开发商进行授权，网址：http://www.jeesns.cn/，QQ：582866070");
    }
}
