package com.chenlf.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.chenlf.blog.dao.pojo.SysUser;
import com.chenlf.blog.service.LoginService;
import com.chenlf.blog.service.SysUserService;
import com.chenlf.blog.utils.JWTUtils;
import com.chenlf.blog.vo.ResponseStates;
import com.chenlf.blog.vo.Result;
import com.chenlf.blog.vo.params.LoginParam;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;



/**
 * 登录
 *
 * @author ChenLF
 * @date 2022/03/15 09:49
 **/
@Slf4j
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    private static final String salt = "codechen!@#";

    @Autowired
    private SysUserService sysUserService;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 登录
     *
     * @param loginParam
     * @return
     */
    @Override
    public Result login(LoginParam loginParam) {
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        //1 检查参数是否合法
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return Result.fail(ResponseStates.PARAMS_ERROR);
        }
        //2 查询用户是否存在
        String pwd = DigestUtils.md5Hex(password + salt);
        SysUser user = sysUserService.findUser(account, pwd);
        if (user == null) {
            return Result.fail(ResponseStates.ACCOUNT_PWD_NOT_EXIST);
        }
        //3 成功 生成token存入redis并返回给前端(登录认证时,先认证token是否合法,再去redis认证是否存在)
        String token = JWTUtils.createToken(user.getId());
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(user), 1, TimeUnit.DAYS);
        logger.debug("登录生成token：{}",token);
        System.out.println("登录生成：token:"+token);
        return Result.success(token);
    }


    /**
     * 登出
     *
     * @param token
     * @return
     */
    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
        return Result.success(null);
    }

    /**
     * 注册
     *
     * @return
     */
    @Override
    public Result register(LoginParam loginParam) {
        //1.参数验证
        //2.判断用户是否存在
        //3.不存在注册用户
        //4.存在 返回错误结果
        //5.注册 生成token
        //6.存redis
        //7.注意: 加上事务
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        String nickname = loginParam.getNickname();
        if (StringUtils.isBlank(account)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(nickname)) {
            return Result.fail(ResponseStates.PARAMS_ERROR);
        }
        SysUser sysUser = sysUserService.findUserByAcount(account);
        if (sysUser != null) {
            return Result.fail(ResponseStates.ACCOUNT_EXIST);
        }
        sysUser = new SysUser();
        sysUser.setNickname(nickname);
        sysUser.setAccount(account);
        sysUser.setPassword(DigestUtils.md5Hex(password + salt));
        sysUser.setCreateDate(System.currentTimeMillis());
        sysUser.setLastLogin(System.currentTimeMillis());
        sysUser.setAvatar("/static/img/logo.b3a48c0.png");
        sysUser.setAdmin(1); //1 为true
        sysUser.setDeleted(0); // 0 为false
        sysUser.setSalt("");
        sysUser.setStatus("");
        sysUser.setEmail("");
        sysUserService.save(sysUser);

        String token = JWTUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(sysUser), 1, TimeUnit.DAYS);

        return Result.success(token);
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("admin" + salt));
    }
}
