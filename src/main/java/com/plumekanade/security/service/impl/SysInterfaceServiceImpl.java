package com.plumekanade.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plumekanade.security.entity.SysInterface;
import com.plumekanade.security.mapper.ISysInterfaceMapper;
import com.plumekanade.security.service.ISysInterfaceService;
import org.springframework.stereotype.Service;

/**
 * @author kanade
 * @date 2020-07-07 17:22
 */
@Service
public class SysInterfaceServiceImpl extends ServiceImpl<ISysInterfaceMapper, SysInterface>
        implements ISysInterfaceService {
}
