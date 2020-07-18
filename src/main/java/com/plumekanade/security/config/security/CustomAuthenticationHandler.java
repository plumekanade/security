package com.plumekanade.security.config.security;

import com.plumekanade.security.constant.ProjectConstant;
import com.plumekanade.security.enums.ResultEnum;
import com.plumekanade.security.service.ISysUserService;
import com.plumekanade.security.utils.ServletUtils;
import com.plumekanade.security.dto.ReturnMessage;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kanade
 * @date 2020-07-08 11:47
 */
@Configuration
public class CustomAuthenticationHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler,
        LogoutSuccessHandler, AccessDeniedHandler, AuthenticationEntryPoint, SessionInformationExpiredStrategy {

    private final ISysUserService userService;

    public CustomAuthenticationHandler(ISysUserService userService) {
        this.userService = userService;
    }

    /**
     * 登录成功处理
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        //更新用户表上次登录时间、更新时间字段
        User user = (User) authentication.getPrincipal();
        userService.updateLoginTime(user.getUsername());

        // TODO
        // 此处还可以进行一些处理，比如登录成功之后可能需要返回给前台当前用户有哪些菜单权限，
        // 进而前台动态的控制菜单的显示等，具体根据自己的业务需求进行扩展

        // 返回数据
        ServletUtils.render(response, ReturnMessage.success(user));
    }

    /**
     * 登录失败异常拦截
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {

        // 返回值
        ReturnMessage msg;

        if (e instanceof AccountExpiredException || e instanceof CredentialsExpiredException) {
            // 账号/密码过期
            msg = ReturnMessage.fail(ResultEnum.USER_PWD_EXPIRED);

        } else if (e instanceof BadCredentialsException) {
            // 密码错误
            msg = ReturnMessage.fail(ResultEnum.USER_PWD_EXCEPTION);

        } else if (e instanceof DisabledException) {
            // 账号不可用
            msg = ReturnMessage.fail(ResultEnum.USER_DISABLE_EXCEPTION);

        } else if (e instanceof LockedException) {
            // 账号锁定
            msg = ReturnMessage.fail(ResultEnum.USER_LOCKED_EXCEPTION);

        } else if (e instanceof InternalAuthenticationServiceException) {
            // 用户不存在
            msg = ReturnMessage.fail(ResultEnum.USER_ACCOUNT_NOT_EXIST);

        } else {
            // 其他错误
            msg = ReturnMessage.fail(ResultEnum.UNKNOWN_EXCEPTION);
        }
        ServletUtils.render(response, msg);
    }

    /**
     * 登出成功处理
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        ServletUtils.render(response, ReturnMessage.success(ProjectConstant.LOGOUT_SUCCESS));
    }

    /**
     * 权限拒绝处理
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ServletUtils.render(response, ReturnMessage.fail(ResultEnum.API_PMS_EXCEPTION));
    }

    /**
     * 匿名用户访问无权限资源时的异常
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {

        ServletUtils.render(response, ReturnMessage.fail(ResultEnum.AUTHENTICATION_EXCEPTION));
    }

    /**
     * 会话信息过期策略（如token过期、被挤下线等）
     */
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) {

        HttpServletResponse response = sessionInformationExpiredEvent.getResponse();
        ServletUtils.render(response, ReturnMessage.fail(ResultEnum.USER_LOGIN_OTHER));
    }
}
