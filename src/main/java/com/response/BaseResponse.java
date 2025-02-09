package com.response;

import lombok.Data;

/**
 * api响应格式
 * */
@Data
public class BaseResponse<T> {
    private int status;
    private String message;
    private T data;

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
