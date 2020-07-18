package com.plumekanade.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plumekanade.security.entity.SysRole;
import com.plumekanade.security.mapper.ISysInterfaceMapper;
import com.plumekanade.security.mapper.ISysRoleMapper;
import com.plumekanade.security.service.ISysRoleService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  kanade
 * @date  2020-07-03 15:24
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<ISysRoleMapper, SysRole> implements ISysRoleService {

    private final ISysRoleMapper roleMapper;
    private final ISysInterfaceMapper interfaceMapper;

    public SysRoleServiceImpl(ISysRoleMapper roleMapper, ISysInterfaceMapper interfaceMapper) {
        this.roleMapper = roleMapper;
        this.interfaceMapper = interfaceMapper;
    }

    @Override
    public List<SimpleGrantedAuthority> findByUserId(Long userId) {

        List<String> roles = roleMapper.rolesByUserId(userId);
        List<SimpleGrantedAuthority> results = new ArrayList<>();

        roles.forEach(role -> results.add(new SimpleGrantedAuthority(role)));

//        for (Long roleId : list) {
//            List<String> paths = interfaceMapper.pathByRoleId(roleId);
//            paths.forEach(path -> results.add(new SimpleGrantedAuthority(path)));
//
//        }
        return results;
    }

    @Override
    public List<String> rolesByUrl(String url) {

        return roleMapper.rolesByUrl(url);
    }
}
