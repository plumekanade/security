package com.plumekanade.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plumekanade.security.entity.SysUserRole;
import com.plumekanade.security.mapper.ISysUserRoleMapper;
import com.plumekanade.security.service.ISysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author  kanade
 * @date  2020-07-03 15:32
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<ISysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
}
