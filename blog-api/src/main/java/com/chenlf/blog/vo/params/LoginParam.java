package com.chenlf.blog.vo.params;

import lombok.Data;

/**
 * @author ChenLF
 * @date 2022/03/15 09:51
 **/
@Data
public class LoginParam {

    private String account;
    private String password;
    private String nickname;
}
