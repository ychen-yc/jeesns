package com.lxinet.jeesns.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lxinet.jeesns.core.enums.Messages;
import com.lxinet.jeesns.core.model.Page;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultModel<T>
        implements Serializable {
    public static final Integer SUCCESS = Integer.valueOf(0);
    public static final Integer ERROR = Integer.valueOf(-1);
    private int code;
    private String message;
    private String url;
    private T data;
    private Page page;

    public ResultModel() {
    }

    public ResultModel(int code) {
        this.code = code;
        if (code == -2) {
            setMessage("参数错误");
        } else if (code == ERROR.intValue()) {
            setMessage(Messages.ERROR);
        } else if (code == SUCCESS.intValue()) {
            this.setMessage(Messages.SUCCESS);
        }
    }

    public ResultModel(T data) {
        if ((data instanceof Boolean)) {
            Boolean flag = (Boolean) data;
            if (flag.booleanValue() == true) {
                setMessage(Messages.SUCCESS);
            } else {
                setMessage(Messages.ERROR);
            }
        } else {
            this.code = SUCCESS.intValue();
            this.data = data;
        }
    }

    public ResultModel(int code, Page page) {
        this.code = code;
        this.page = page;
    }

    public ResultModel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultModel(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultModel(int code, String message, String url) {
        this.code = code;
        this.message = message;
        this.url = url;
    }

    public ResultModel(Messages message) {
        this.code = message.getCode();
        this.message = message.getMessage();
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Page getPage() {
        return this.page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public void setMessage(Messages message) {
        this.code = message.getCode();
        this.message = message.getMessage();
    }

    public String toString() {
        return super.toString();
    }
}
