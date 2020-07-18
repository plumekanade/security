package com.plumekanade.security.config.security;

import com.plumekanade.security.service.ISysRoleService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.Collection;
import java.util.List;

/**
 * @author kanade
 * @date 2020-07-08 11:19
 */
@Configuration
public class CustomMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final ISysRoleService roleService;

    public CustomMetadataSource(ISysRoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        //获取请求地址 去除get请求的 ?xxx=yyy 参数
        String requestUrl = ((FilterInvocation) o).getRequestUrl().split("\\?")[0];

        //查询具体某个接口的权限
        List<String> roleList = roleService.rolesByUrl(requestUrl);
        if (roleList == null || roleList.size() == 0) {
            //请求路径没有配置权限，表明该请求接口可以任意访问
            return null;
        }

        return SecurityConfig.createList(roleList.toArray(new String[0]));
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
