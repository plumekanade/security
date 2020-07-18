package com.plumekanade.security.controller;

import com.plumekanade.security.constant.ProjectConstant;
import com.plumekanade.security.dto.ReturnMessage;
import com.plumekanade.security.entity.SysUser;
import com.plumekanade.security.enums.ResultEnum;
import com.plumekanade.security.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kanade
 * @date 2020-07-18 09:19
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final ISysUserService sysUserService;

    public UserController(ISysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 添加用户
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ReturnMessage addUser(@RequestBody SysUser user) {

        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return ReturnMessage.fail(ResultEnum.NAME_PWD_NOT_FOUND);
        }

        //防止带入id
        user.setId(null);
        user.setName(StringUtils.isBlank(user.getName()) ? user.getUsername() : user.getName());
        sysUserService.addUser(user);

        return ReturnMessage.success();
    }

}
