package com.lxinet.jeesns.core.exception;

import com.lxinet.jeesns.core.enums.Messages;

public class NotFountException
        extends JeeException {
    public NotFountException(Messages messages) {
        super(messages);
    }

    public NotFountException(String msg) {
        super(msg + "不存在");
    }
}
