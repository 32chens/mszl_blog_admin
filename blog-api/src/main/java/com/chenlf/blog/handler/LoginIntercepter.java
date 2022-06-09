package com.chenlf.blog.handler;

import com.alibaba.fastjson.JSON;
import com.chenlf.blog.dao.pojo.SysUser;
import com.chenlf.blog.service.TokenService;
import com.chenlf.blog.utils.UserThreadLocal;
import com.chenlf.blog.vo.ResponseStates;
import com.chenlf.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * @author ChenLF
 * @date 2022/05/23 19:49
 **/
@Slf4j
@Component
public class LoginIntercepter implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        String token = request.getHeader("Authorization");
        log.info("request uri:{}", request.getRequestURI());
        log.info("request method:{}", request.getMethod());
        log.info("request token:{}", token);

        SysUser user = tokenService.checkToken(token);
        if (user == null){
            Result result = Result.fail(ResponseStates.NO_LOGIN);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        UserThreadLocal.set(user);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
