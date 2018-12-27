package com.lxinet.jeesns.core.enums;

import com.lxinet.jeesns.core.utils.LocaleUtil;
import com.lxinet.jeesns.core.utils.StringUtils;

import java.text.MessageFormat;

public enum Messages {
    PARAM_ERROR(-1001, "param_error", new Object[0]),
    NOT_EMPTY(-1001, "not_empty", new Object[0]),
    USERNAME_NOT_EMPTY(-1001, "not_empty", new Object[]{Messages("username", new Object[0])}),
    PASSWORD_NOT_EMPTY(-1001, "not_empty", new Object[]{Messages("password", new Object[0])}),
    LOGIN_NAME_NOT_EMPTY(-1001, "not_empty", new Object[]{Messages("login_name", new Object[0])}),
    TOKEN_NOT_EMPTY(-1001, "not_empty", new Object[]{Messages("token", new Object[0])}),
    NAME_NOT_EMPTY(-1001, "not_empty", new Object[]{Messages("name", new Object[0])}),
    CATE_NOT_EMPTY(-1001, "not_empty", new Object[]{Messages("cate", new Object[0])}),
    CATEGORY_MUST_BE_SELECT(-1001, "category_must_be_select", new Object[0]),
    PRICE_IS_ERROR(-1001, "price_is_error", new Object[0]),
    STOCK_MUST_BE_INTEGER(-1001, "stock_must_be_integer", new Object[0]),
    VIRTUALSELLNUM_MUST_BE_INTEGER(-1001, "virtualsellnum_must_be_integer", new Object[0]),
    NOT_EXISTS(-1007, "not_exists", new Object[0]),
    ADMIN_NOT_EXISTS(-1002, "not_exists", new Object[]{Messages("admin", new Object[0])}),
    USER_NOT_EXISTS(-1002, "not_exists", new Object[]{Messages("user", new Object[0])}),
    WEIBO_NOT_EXISTS(-1002, "not_exists", new Object[]{Messages("weibo", new Object[0])}),
    COMMENT_NOT_EXISTS(-1002, "not_exists", new Object[]{Messages("comment", new Object[0])}),
    ARTICLE_NOT_EXISTS(-1002, "not_exists", new Object[]{Messages("article", new Object[0])}),
    GROUP_NOT_EXISTS(-1002, "not_exists", new Object[]{Messages("group", new Object[0])}),
    TOPIC_NOT_EXISTS(-1002, "not_exists", new Object[]{Messages("topic", new Object[0])}),
    PICTURE_NOT_EXISTS(-1002, "not_exists", new Object[]{Messages("picture", new Object[0])}),
    AD_NOT_EXISTS(-1002, "ad_not_exists", new Object[0]),
    PARENT_CATE_NOT_EXISTS(-1002, "parent_cate_not_exists", new Object[0]),
    EXISTS_LOWER_CATEGORY(-1003, "exists_lower_category", new Object[0]),
    UN_LOGIN(-1002, "un_login", new Object[0]),
    LOGIN_INFO_WRONG(-1003, "login_info_wrong", new Object[0]),
    ACCOUNT_IS_DISABLED(-1004, "account_is_disabled", new Object[0]),
    USERNAME_EXISTS(-1005, "exists", new Object[]{Messages("username", new Object[0])}),
    EMAIL_EXISTS(-1005, "exists", new Object[]{Messages("email", new Object[0])}),
    USERNAME_LENGTH_ONLY_BE(-1006, "username_length_only_be", new Object[]{Integer.valueOf(5), Integer.valueOf(32)}),
    PARENT_CONNOT_BE_SELF(-1007, "parent_connot_be_self", new Object[0]),
    ONLY_TOP_CATE_CAN_ADD(-1007, "only_top_cate_can_add", new Object[0]),
    DELETE_SUB_CATE_FIRST(-1007, "delete_sub_cate_first", new Object[0]),
    CATE_NOT_EXISTS(-1007, "cate_not_exists", new Object[0]),
    LOGIN_CLOSED(-1007, "login_closed", new Object[0]),
    CONTRIBUTION_CLOSED(-1007, "contribution_closed", new Object[0]),
    CONTENT_NOT_EMPTY(-1007, "not_empty", new Object[]{Messages("content", new Object[0])}),
    FRIIEND_LINK_NOT_EXISTS(-1007, "not_exists", new Object[]{Messages("friiend_link", new Object[0])}),
    PICTURE_ALBUM_NOT_EXISTS(-1007, "not_exists", new Object[]{Messages("picture_album", new Object[0])}),
    ERROR(-1, "operate_error", new Object[0]),
    SUCCESS(0, "operate_success", new Object[0]);

    private int code;
    private String message;

    private static String Messages(String msgKey, Object... args) {
        return LocaleUtil.getMessageSource().getMessage(msgKey, args, LocaleUtil.getLocale());
    }

    private Messages(int code, String msgKey, Object... args) {
        this.code = code;
        if (StringUtils.isNotEmpty(msgKey)) {
            System.out.print(111);
            String mm = LocaleUtil.getMessageSource().getMessage(msgKey, args, LocaleUtil.getLocale());
            System.out.print(111);
            this.message = MessageFormat.format(LocaleUtil.getMessageSource().getMessage(msgKey, args, LocaleUtil.getLocale()), args);
        } else {
            this.message = msgKey;
        }
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
