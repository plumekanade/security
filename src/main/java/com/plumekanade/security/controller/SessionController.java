package com.plumekanade.security.controller;

import com.plumekanade.security.enums.ResultEnum;
import com.plumekanade.security.dto.ReturnMessage;
import com.plumekanade.security.utils.UserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author kanade
 * @date 2020-07-03 17:06
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    /**
     * session失效处理
     */
    @RequestMapping("/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ReturnMessage invalid() {
        return ReturnMessage.fail(ResultEnum.USER_EXPIRED);
    }


    /**
     * 具有权限角色 user, admin 才能访问
     */
    @RequestMapping("/normal")
    public ReturnMessage normal() {

        String username = Objects.requireNonNull(UserUtils.getUser()).getUsername();
        return ReturnMessage.success("normal------" + username);
    }

    /**
     * 具有权限角色 admin 才能访问
     */
    @RequestMapping("/admin")
    public ReturnMessage admin() {

        String username = Objects.requireNonNull(UserUtils.getUser()).getUsername();
        return ReturnMessage.success("admin-----" + username);
    }

    /**
     * 任意访问
     */
    @RequestMapping("/any")
    public ReturnMessage any() {

        return ReturnMessage.success("any");
    }
}
