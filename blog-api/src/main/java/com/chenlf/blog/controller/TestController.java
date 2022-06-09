package com.chenlf.blog.controller;

import com.chenlf.blog.utils.UserThreadLocal;
import com.chenlf.blog.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author ChenLF
 * @date 2022/05/23 20:02
 **/

@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping
    public Result test(){
        return Result.success(UserThreadLocal.get());
    }
}
