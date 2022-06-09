package com.chenlf.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.chenlf.blog.dao.pojo.SysUser;
import com.chenlf.blog.service.TokenService;
import com.chenlf.blog.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 
 * @author ChenLF
 * @date 2022/05/20 20:54
 **/

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public SysUser checkToken(String token) {
        /**
         * token合法性校验
         * 是否为空，解析是否成功，redis是否存在
         * 失败返回错误
         * 成功，返回结果
         */
        if (StringUtils.isBlank(token)) {
            return null;
        }
        Map<String, Object> checkToken = JWTUtils.checkToken(token);
        if (checkToken == null) {
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)) {
            return null;
        }
        SysUser user = JSON.parseObject(userJson, SysUser.class);

        return user;
    }
}
