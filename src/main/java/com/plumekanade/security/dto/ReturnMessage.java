package com.plumekanade.security.dto;

import com.plumekanade.security.constant.ProjectConstant;
import com.plumekanade.security.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回值封装类
 *
 * @author kanade
 * @date 2020-06-05 14:29
 */
@Data
public class ReturnMessage implements Serializable {

    private static final long serialVersionUID = 7147657664562997212L;

    private int code;

    private String msg;

    private Object data;

    private ReturnMessage(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ReturnMessage success(Object data) {

        return new ReturnMessage(200, "操作成功", data);
    }

    public static ReturnMessage success() {

        return new ReturnMessage(200, "操作成功", ProjectConstant.OK);
    }

    public static ReturnMessage fail(int code, String msg, Object data) {

        return new ReturnMessage(code, msg, data);
    }


    public static ReturnMessage fail(int code, String msg) {

        return new ReturnMessage(code, msg, null);
    }

    public static ReturnMessage fail(ResultEnum resultEnum) {

        return new ReturnMessage(resultEnum.getCode(), resultEnum.getMsg(), null);
    }
}
