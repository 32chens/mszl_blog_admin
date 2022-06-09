package com.chenlf.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.chenlf.blog.dao.mapper.SysUserMapper;
import com.chenlf.blog.dao.pojo.SysUser;
import com.chenlf.blog.service.LoginService;
import com.chenlf.blog.service.SysUserService;
import com.chenlf.blog.service.TokenService;
import com.chenlf.blog.vo.LoginUserVo;
import com.chenlf.blog.vo.ResponseStates;
import com.chenlf.blog.vo.Result;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ChenLF
 * @date 2022/03/13 17:22
 **/
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private TokenService tokenService;

    @Override
    public SysUser findUserById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null) {
            sysUser = new SysUser();
            sysUser.setNickname("默认名称");
        }
        return sysUser;
    }

    @Override
    public SysUser findUser(String account, String pwd) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount, account);
        queryWrapper.eq(SysUser::getPassword, pwd);
        queryWrapper.last("limit 1");
        queryWrapper.select(SysUser::getId, SysUser::getAccount, SysUser::getAvatar, SysUser::getNickname);
        SysUser user = sysUserMapper.selectOne(queryWrapper);
        return user;
    }

    /**
     * 根据token查询用户信息
     * @param token
     * @return
     */
    @Override
    public Result getUserInfoByToken(String token) {
        SysUser user = tokenService.checkToken(token);
        if (user == null) {
            return Result.fail(ResponseStates.TOKEN_ERROR);
        }
        LoginUserVo loginUserVo = new LoginUserVo();
        BeanUtils.copyProperties(user, loginUserVo);
        loginUserVo.setId(String.valueOf(user.getId()));
        return Result.success(loginUserVo);
    }

    /**
     * 根据账户查询用户
     *
     * @return
     */
    @Override
    public SysUser findUserByAcount(String account) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount, account);
        queryWrapper.last("limit 1");
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        return sysUser;
    }

    @Override
    public void save(SysUser sysUser) {
        //这里使用的时mybatisplus框架,id生成的时分布式id
        sysUserMapper.insert(sysUser);
    }
}
