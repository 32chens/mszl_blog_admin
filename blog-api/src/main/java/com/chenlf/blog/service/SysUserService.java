package com.chenlf.blog.service;

import com.chenlf.blog.dao.pojo.SysUser;
import com.chenlf.blog.vo.Result;

public interface SysUserService {
    SysUser findUserById(Long id);

    SysUser findUser(String account, String pwd);

    /**
     * 根据token获取用户信息
     *
     * @param token
     * @return Result
     */
    Result getUserInfoByToken(String token);

    /**
     * 根据账户查询用户
     *
     * @return
     */
    SysUser findUserByAcount(String account);

    /**
     * 保存用户
     * @param sysUser
     */
    void save(SysUser sysUser);


}
