package com.plumekanade.security.exception;

import com.plumekanade.security.dto.ReturnMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 项目异常拦截类
 *
 * @author kanade
 * @date 2020-06-22 14:41
 */
@ControllerAdvice
public class ProjectExceptionHandler {

    /**
     * Jackson转换异常拦截
     */
    @ResponseBody
    @ExceptionHandler(ConvertException.class)
    public ReturnMessage handlerJsonProcessingException(ConvertException e) {

        return ReturnMessage.fail(e.getCode(), e.getMsg());
    }

    /**
     * 用户相关异常拦截
     */
    @ResponseBody
    @ExceptionHandler(SysUserException.class)
    public ReturnMessage handlerSysUserException(SysUserException e) {

        return ReturnMessage.fail(e.getCode(), e.getMsg());
    }
}
