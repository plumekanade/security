package com.plumekanade.security.config.security;

import com.plumekanade.security.service.ISysUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * 自定义的SpringSecurity配置类
 *
 * @author kanade
 * @date 2020-06-23 10:16
 */
@Configuration
@EnableWebSecurity  // 开启Security
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ISysUserService userService;
    private final DecisionManager decisionManager;
    private final CustomMetadataSource metadataSource;
    private final CustomSecurityInterceptor securityInterceptor;
    private final CustomAuthenticationHandler authenticationHandler;

    CustomSecurityConfig(ISysUserService userService, DecisionManager decisionManager,
                         CustomMetadataSource metadataSource, CustomSecurityInterceptor securityInterceptor,
                         CustomAuthenticationHandler authenticationHandler) {
        this.userService = userService;
        this.metadataSource = metadataSource;
        this.decisionManager = decisionManager;
        this.securityInterceptor = securityInterceptor;
        this.authenticationHandler = authenticationHandler;
    }

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        // 设置默认的加密方式（强hash方式加密）
//        return new BCryptPasswordEncoder();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();

        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(decisionManager);    // 决策管理器
                        o.setSecurityMetadataSource(metadataSource);    // 安全元数据源
                        return o;
                    }
                })

                // 登出
                .and().logout()
                .logoutUrl("/logout")
                .permitAll()    // 允许所有用户
                .logoutSuccessHandler(authenticationHandler)    // 登出成功处理
                .deleteCookies("token")    // 登出之后删除cookie

                // 登入
                .and().formLogin()
                .loginProcessingUrl("/login")
                .passwordParameter("password")
                .usernameParameter("username")
                .permitAll()    // 允许所有用户
                .successHandler(authenticationHandler)   // 登录成功处理
                .failureHandler(authenticationHandler)  // 登录失败处理

                // 异常处理(权限拒绝、登录失效等)
                .and().exceptionHandling()
                .accessDeniedHandler(authenticationHandler)   // 权限拒绝处理
                .authenticationEntryPoint(authenticationHandler)     // 匿名用户访问无权限资源时的异常处理

                // 会话管理
                .and().sessionManagement()
                .invalidSessionUrl("/session/invalid")      // session超时处理
                .maximumSessions(1)     // 同一账号同时登录最大用户数
                .expiredSessionStrategy(authenticationHandler);     // 会话失效(账号被挤下线)处理逻辑

        http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);
    }


}
