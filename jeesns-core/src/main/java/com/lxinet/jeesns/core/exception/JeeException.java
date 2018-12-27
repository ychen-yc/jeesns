package com.lxinet.jeesns.core.exception;

import com.lxinet.jeesns.core.enums.Messages;

public class JeeException
        extends RuntimeException {
    private Messages jeeMessage;

    public JeeException() {
        this.jeeMessage = Messages.ERROR;
    }

    public JeeException(String msg) {
        super(msg);
    }

    public JeeException(Messages lxiMessage) {
        this.jeeMessage = lxiMessage;
    }

    public Messages getJeeMessage() {
        return this.jeeMessage;
    }
}
