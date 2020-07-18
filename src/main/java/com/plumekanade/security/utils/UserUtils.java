package com.plumekanade.security.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * 用户工具类
 *
 * @author kanade
 * @date 2020-07-18 10:45
 */
public class UserUtils {

    /**
     * SecurityContextHolder.getContext()获取安全上下文对象, 就是那个保存在 ThreadLocal 里面的安全上下文对象
     * 总是不为null(如果不存在, 则创建一个 authentication 属性为 null 的 empty 安全上下文对象)
     * 获取当前认证了的 principal(当事人), 或者 request token (令牌)
     * 如果没有认证，会是 null, 该例子是认证之后的情况
     */
    public static User getUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //有登陆用户就返回登录用户，没有就返回null
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }

}
