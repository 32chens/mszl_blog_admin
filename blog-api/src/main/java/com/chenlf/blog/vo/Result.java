package com.chenlf.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author ChenLF
 * @date 2022/05/18 21:26
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private long timestamp;
    private boolean success;
    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data) {
        return Result.<T>builder().data(data)
                .success(true)
                .msg(ResponseStates.SUCCESS.getDescription())
                .code(ResponseStates.SUCCESS.getResponseCode())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public static <T> Result<T> fail(ResponseStates responseStates,T data) {
        return Result.<T>builder().data(data)
                .success(false)
                .msg(responseStates.getDescription())
                .code(responseStates.getResponseCode())
                .timestamp(System.currentTimeMillis())
                .build();
    }
    public static <T> Result<T> fail(ResponseStates responseStates) {
        return Result.fail(responseStates,null);
    }

    public static <T> Result<T> fail(int code, String message) {
        return Result.<T>builder().data(null)
                .success(false)
                .msg(message)
                .code(code)
                .timestamp(System.currentTimeMillis())
                .build();
    }
}
