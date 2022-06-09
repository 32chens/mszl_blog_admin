package com.chenlf.blog.controller;

import com.chenlf.blog.service.LoginService;
import com.chenlf.blog.vo.Result;
import com.chenlf.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 
 * @author ChenLF
 * @date 2022/05/22 14:17
 **/

@RestController
@RequestMapping("loginout")
public class LonginOutController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result logout(@RequestHeader("Authorization") String token){
        return loginService.logout(token);
    }
}
