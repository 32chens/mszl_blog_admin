package com.chenlf.blog.service;

import com.chenlf.blog.dao.pojo.SysUser;

public interface TokenService {
    /**
     * 验证token
     *
     * @param token
     * @return SysUser
     */
    public SysUser checkToken(String token);

}
