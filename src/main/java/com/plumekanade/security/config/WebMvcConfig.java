package com.plumekanade.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web拦截器管理器
 *
 * @author kanade
 * @date 2020-06-24 11:59
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 这个方法是用来配置静态资源
     * 比如html，js，css，等等（前后端不分离使用）
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:static/*");
    }

    /**
     * 这个方法用来注册拦截器
     * 写好的拦截器需要通过这里添加注册才能生效
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //加入登录验证拦截器
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns("/**")     //哪些路径/请求需要拦截
//                .excludePathPatterns(applicationConfig.getExcludePath());   //哪些路径/请求排除在外
    }

    /**
     * 配置跨域拦截器
     **/
    @Bean
    public CorsFilter corsFilter() {

        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        return new CorsFilter(configSource);
    }

}
