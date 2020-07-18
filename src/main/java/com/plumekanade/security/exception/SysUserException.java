package com.plumekanade.security.exception;

/**
 * 系统用户相关异常
 *
 * @author kanade
 * @date 2020-06-23 16:56
 */
public class SysUserException extends RuntimeException {

    private static final long serialVersionUID = 5218723445855178954L;

    private int code;
    private String msg;

    public SysUserException(int code, String msg) {
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
