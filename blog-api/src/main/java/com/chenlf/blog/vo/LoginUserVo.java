package com.chenlf.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 
 * @author ChenLF
 * @date 2022/05/20 20:38
 **/
@Data
public class LoginUserVo {
//    @JsonSerialize(using = ToStringSerializer.class)
    private String id;

    private String account;

    private String nickname;

    private String avatar;
}
