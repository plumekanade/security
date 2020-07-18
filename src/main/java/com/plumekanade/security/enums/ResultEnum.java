package com.plumekanade.security.enums;

/**
 * 返回值枚举
 *
 * @author kanade
 * @date 2020-06-22 14:47
 */
public enum ResultEnum {

    AUTHENTICATION_EXCEPTION(601, "用户未登录"),
    API_PMS_EXCEPTION(602, "未有该接口权限"),
    USER_PWD_EXCEPTION(603, "密码错误"),
    USER_DISABLE_EXCEPTION(604, "用户被禁用"),
    USER_LOCKED_EXCEPTION(605, "用户被锁定"),
    USER_PWD_EXPIRED(606, "密码已过期"),
    USER_ACCOUNT_NOT_EXIST(607, "用户不存在"),
    USER_LOGIN_OTHER(608, "用户在别处登录"),
    USER_EXPIRED(609, "用户凭证已过期, 请重新登录"),
    SYS_USER_NOT_FOUND(610, "用户名为空"),
    UNKNOWN_EXCEPTION(650, "未知登录错误"),


    JACKSON_EXCEPTION(1001, "Jackson转化异常"),
    NAME_PWD_NOT_FOUND(1002, "用户名或密码为空"),
    PARAM_NOT_VALID(1003, "参数无效"),
    PARAM_IS_BLANK(1004, "参数为空"),
    PARAM_LACK(1005, "参数缺失"),

    ;

    private int code;
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
