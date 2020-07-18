package com.plumekanade.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.plumekanade.security.entity.SysRole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

/**
 * @author  kanade
 * @date  2020-07-03 15:24
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 根据用户id查找对应的角色列表
     */
    List<SimpleGrantedAuthority> findByUserId(Long id);

    /**
     * 根据请求地址url查找角色名称列表
     */
    List<String> rolesByUrl(String url);
}
