package com.chenlf.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum ResponseStates {
    PARAMS_ERROR(10001, "参数有误"),
    ACCOUNT_PWD_NOT_EXIST(10002, "用户名或密码不存在"),
    TOKEN_ERROR(10003, "token不合法"),
    ACCOUNT_EXIST(10004, "账号已存在"),
    NO_PERMISSION(70001, "无访问权限"),
    SESSION_TIME_OUT(90001, "会话超时"),

    SYSTEM_EXCEPTION(-999, "系统异常"),

    NO_LOGIN(90002, "未登录"),
    SUCCESS(200, "success"),
    FAIL(600, "failed");

    private int responseCode;

    private String description;

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
