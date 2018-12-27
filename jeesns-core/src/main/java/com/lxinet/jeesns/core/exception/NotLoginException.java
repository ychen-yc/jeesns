package com.lxinet.jeesns.core.exception;

import com.lxinet.jeesns.core.enums.Messages;

public class NotLoginException
        extends JeeException {
    public NotLoginException() {
        super(Messages.UN_LOGIN);
    }
}
