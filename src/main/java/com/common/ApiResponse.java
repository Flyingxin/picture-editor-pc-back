package com.common;

/**
 * api响应格式
 * */
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;

    // getters...
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }




    // setters...
    private void setData(T data) {
        this.data = data;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    private void setStatus(int status) {
        this.status = status;
    }


    public static <T> ApiResponse<T> success(T data, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(200);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static <T> ApiResponse<T> error(int status, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }
}
