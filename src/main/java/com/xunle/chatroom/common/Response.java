package com.xunle.chatroom.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xunle
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    public static Response ok() {
        Response response = new Response();
        response.setCode(ResultCode.SUCCESS.getCode());
        response.setMessage(ResultCode.SUCCESS.getMessage());
        return response;
    }

    public static Response error() {
        Response response = new Response();
        response.setCode(ResultCode.ERROR.getCode());
        response.setMessage(ResultCode.ERROR.getMessage());
        return response;
    }

    public Response code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Response message(String message) {
        this.setMessage(message);
        return this;
    }

    public Response data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public Response data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
