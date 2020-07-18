package com.plumekanade.security.exception;

/**
 * 转换异常
 *
 * @author kanade
 * @date 2020-06-22 14:53
 */
public class ConvertException extends RuntimeException {

    private static final long serialVersionUID = 8267733889293004527L;

    private int code;
    private String msg;

    public ConvertException(int code, String msg) {
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
