package com.plumekanade.security.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;

/**
 * Servlet相关工具类
 *
 * @author kanade
 * @date 2020-06-24 14:12
 */
@Slf4j
public class ServletUtils {

    /**
     * 渲染到客户端
     *
     * @param object 待渲染的实体类, 会自动转为json
     */
    public static void render(HttpServletResponse response, Object object) {

        // 允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许自定义请求头token(允许head跨域)
        response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
        response.setHeader("Content-type", "application/json;charset=UTF-8");

        try {
            response.getWriter().print(MapperUtils.toString(object));
        } catch (Exception e) {
            log.error("render方法响应异常: ", e);
        }
    }
}
