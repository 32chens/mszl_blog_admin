package com.chenlf.blog.controller;

import com.chenlf.blog.service.LoginService;
import com.chenlf.blog.vo.Result;
import com.chenlf.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author ChenLF
 * @date 2022/05/22 14:22
 **/

@RestController
@RequestMapping("register")
public class RegisterController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result regiser(@RequestBody LoginParam loginParam){
        return loginService.register(loginParam);
    }
}
