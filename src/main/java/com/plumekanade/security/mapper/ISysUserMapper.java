package com.plumekanade.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.plumekanade.security.entity.SysUser;
import org.springframework.stereotype.Repository;

/**
 * 系统用户Mapper
 *
 * @author kanade
 * @date 2020-06-23 10:42
 */
@Repository
public interface ISysUserMapper extends BaseMapper<SysUser> {
}
