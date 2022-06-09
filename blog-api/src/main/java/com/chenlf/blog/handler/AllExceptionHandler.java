package com.chenlf.blog.handler;

import com.chenlf.blog.vo.ResponseStates;
import com.chenlf.blog.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ChenLF
 * @date 2022/03/13 22:20
 **/
//对加了@Controller注解的方法进行拦截处理 AOP实现
@ControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result doExceeption(Exception e) {
        e.printStackTrace();
        return Result.fail(ResponseStates.SYSTEM_EXCEPTION);
    }
}
