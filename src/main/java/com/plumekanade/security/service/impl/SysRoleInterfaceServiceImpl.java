package com.plumekanade.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plumekanade.security.entity.SysRoleInterface;
import com.plumekanade.security.mapper.ISysRoleInterfaceMapper;
import com.plumekanade.security.service.ISysRoleInterfaceService;
import org.springframework.stereotype.Service;

/**
 * @author  kanade
 * @date  2020-07-07 17:23
 */
@Service
public class SysRoleInterfaceServiceImpl extends ServiceImpl<ISysRoleInterfaceMapper, SysRoleInterface>
        implements ISysRoleInterfaceService {
}
