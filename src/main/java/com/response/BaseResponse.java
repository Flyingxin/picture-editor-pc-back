package com.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * api响应格式
 * */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // 自动省略为null的字段
public class BaseResponse<T> {
    private int status;
    private String message;
    private T data;
    private String token;

    // token
    public static <T> BaseResponse<T> success(T data, String message, String token) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setStatus(200);
        response.setMessage(message);
        response.setData(data);
        response.setToken(token);
        return response;
    }

    public static <T> BaseResponse<T> success(T data, String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setStatus(200);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static <T> BaseResponse<T> fail(int status, String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }

    public static <T> BaseResponse<T> authFail() {
        BaseResponse<T> response = new BaseResponse<>();
        response.setStatus(401);
        response.setMessage("身份信息验证失败，请重新登录");
        return response;
    }

}
