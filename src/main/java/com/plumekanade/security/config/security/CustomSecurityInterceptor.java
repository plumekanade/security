package com.plumekanade.security.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.*;
import java.io.IOException;

/**
 * 权限拦截器
 *
 * @author kanade
 * @date 2020-07-08 15:07
 */
@Configuration
public class CustomSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    private final CustomMetadataSource metadataSource;

    public CustomSecurityInterceptor(CustomMetadataSource metadataSource, DecisionManager decisionManager) {
        this.metadataSource = metadataSource;
        super.setAccessDecisionManager(decisionManager);
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.metadataSource;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        invoke(new FilterInvocation(request, response, filterChain));
    }

    public void invoke(FilterInvocation filter) throws IOException, ServletException {

        //filter里面有一个被拦截的url
        //里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
        //再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
        InterceptorStatusToken token = super.beforeInvocation(filter);
        try {
            //执行下一个拦截器
            filter.getChain().doFilter(filter.getRequest(), filter.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }
}
