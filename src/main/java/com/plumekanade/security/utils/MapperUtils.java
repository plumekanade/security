package com.plumekanade.security.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plumekanade.security.enums.ResultEnum;
import com.plumekanade.security.exception.ConvertException;
import lombok.extern.slf4j.Slf4j;


/**
 * jackson包ObjectMapper工具类
 *
 * @author kanade
 * @date 2020-06-22 14:04
 */
@Slf4j
public class MapperUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        //在反序列化时忽略在 json 中存在但 Java 对象不存在的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //在序列化时日期格式默认为 yyyy-MM-dd HH:mm:ss
        mapper.getSerializationConfig().with(DateUtils.dateTime);
        //在序列化时忽略值为 null 的属性
//        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //忽略值为默认值的属性
//        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_DEFAULT);
    }

    /**
     * 将实体类对象转换成json字符串
     */
    public static String toString(Object object) {

        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Jackson转换String异常: ", e);
            throw new ConvertException(ResultEnum.JACKSON_EXCEPTION.getCode(), e.getMessage());
        }
    }

    /**
     * 将json字符串转换成对应的实体类对象
     */
    public static <T> T toObject(String json, Class<T> tClass) {

        try {
            return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            log.error("Jackson转换 {} 类异常: ", tClass, e);
            throw new ConvertException(ResultEnum.JACKSON_EXCEPTION.getCode(), e.getMessage());
        }
    }

}
