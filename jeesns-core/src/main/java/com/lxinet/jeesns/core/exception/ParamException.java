package com.lxinet.jeesns.core.exception;

import com.lxinet.jeesns.core.enums.Messages;

public class ParamException
        extends JeeException {
    public ParamException(String msg) {
        super(msg);
    }

    public ParamException(Messages messages) {
        super(messages);
    }

    public ParamException() {
        super(Messages.PARAM_ERROR);
    }
}
