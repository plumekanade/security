package com.plumekanade.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plumekanade.security.entity.SysUser;
import com.plumekanade.security.enums.ResultEnum;
import com.plumekanade.security.exception.SysUserException;
import com.plumekanade.security.mapper.ISysUserMapper;
import com.plumekanade.security.service.ISysRoleService;
import com.plumekanade.security.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * 系统用户Service实现类
 *
 * @author kanade
 * @date 2020-06-23 10:41
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<ISysUserMapper, SysUser>
        implements ISysUserService, UserDetailsService {

    private final ISysUserMapper userMapper;
    private final ISysRoleService roleService;

    public SysUserServiceImpl(ISysUserMapper userMapper, ISysRoleService roleService) {
        this.userMapper = userMapper;
        this.roleService = roleService;
    }

    /**
     * 实现 UserDetailsService 接口中的 loadUserByUsername 方法
     * 执行登录, 构建 Authentication 对象必须的信息
     * 如果用户不存在, 则抛出 SysUserException 异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (StringUtils.isBlank(username)) {
            log.error("用户名为空~");
            throw new SysUserException(ResultEnum.SYS_USER_NOT_FOUND.getCode(),
                    ResultEnum.SYS_USER_NOT_FOUND.getMsg());
        }

        SysUser user = findByUsername(username);
        if (null == user) {
            log.info("用户名 {} 不存在", username);
            throw new UsernameNotFoundException("用户名 " + username + " 不存在");
        }

        return new User(user.getUsername(), user.getPassword(), user.getUserStatus() == 1,
                true, true, user.getLockStatus() == 1, user.getAuthorities());
    }

    @Override
    public SysUser findByUsername(String username) {

        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        SysUser user = userMapper.selectOne(wrapper);
        if (null != user) {
            user.setAuthorities(roleService.findByUserId(user.getId()));
        }
        return user;
    }

    @Override
    public SysUser findById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public void updateLoginTime(String username) {

        UpdateWrapper<SysUser> wrapper = new UpdateWrapper<>();
        SysUser user = new SysUser();

        wrapper.eq("username", username);
        user.setLoginTime(new Date());
        user.setUsername(username);
        user.setUpdateTime(new Date());

        userMapper.update(user, wrapper);
    }

    @Override
    public void addUser(SysUser user) {

        // 强哈希加密
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        userMapper.insert(user);
    }
}
