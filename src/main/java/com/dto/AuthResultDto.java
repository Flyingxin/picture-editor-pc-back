package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor  // 自动生成的构造函数
public class AuthResultDto<T> {
    /**
     * 验证结果，true：通过，false：不通过
     */
    private boolean result;
    /**
     * 验证结果描述
     */
    private String message;
    /**
     * 验证结果
     */
    private T data;

    public boolean auth() {
        return this.result;
    }

    //    类构造函数
    //    public SessionAuthResultDto(boolean result, String message, T data) {
    //        this.result = result;
    //        this.message = message;
    //        this.data = data;
    //    }

    public static <T> AuthResultDto<T> success(T data) {
        return new AuthResultDto<>(true, "验证通过", data);
    }

    public static <T> AuthResultDto<T> success() {
        return new AuthResultDto<>(true, "验证通过", null);
    }

    public static <T> AuthResultDto<T> fail() {
        return new AuthResultDto<>(false, "验证失败", null);
    }

    public static <T> AuthResultDto<T> fail(String message) {
        return new AuthResultDto<>(false, message, null);
    }
}
