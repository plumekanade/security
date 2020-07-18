package com.plumekanade.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.plumekanade.security.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 系统用户Service接口
 *
 * @author kanade
 * @date 2020-06-23 10:40
 */
public interface ISysUserService extends IService<SysUser>, UserDetailsService {

    /**
     * 根据用户名查找用户对象
     */
    SysUser findByUsername(String username);

    /**
     * 根据id查找用户对象
     */
    SysUser findById(Long id);

    /**
     * 根据用户名更新最后登录时间
     */
    void updateLoginTime(String username);

    /**
     * 添加用户
     */
    void addUser(SysUser user);
}
