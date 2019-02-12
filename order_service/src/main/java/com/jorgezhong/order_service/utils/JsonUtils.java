package com.jorgezhong.order_service.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Project <springcloud-learning>
 *  json工具类
 *
 * @author jorgezhong
 * @date 2018/11/15 10:10
 */
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * json字符串转jsonNode对象的方法
     */
    public static JsonNode str2JsonNode(String str){

        try {
            return objectMapper.readTree(str);
        } catch (IOException e) {
            return null;
        }

    }

}
