package com.plumekanade.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement    //开启事务管理
@MapperScan("com.plumekanade.security.mapper")    //设置mapper接口扫描地址
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 7200)    //设置session过期时间 秒
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

}
