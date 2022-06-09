package com.chenlf.blog.service;


import com.chenlf.blog.dao.pojo.SysUser;
import com.chenlf.blog.vo.Result;
import com.chenlf.blog.vo.params.LoginParam;

public interface LoginService {

    /**
     * 登录
     * @param loginParam
     * @return
     */
    Result login(LoginParam loginParam);

    /**
     * 登出
     * @param token
     * @return
     */
    Result logout(String token);

    Result register(LoginParam loginParam);
}
