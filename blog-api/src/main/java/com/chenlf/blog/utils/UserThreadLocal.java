package com.chenlf.blog.utils;

import com.chenlf.blog.dao.pojo.SysUser;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ChenLF
 * @date 2022/05/24 21:02
 **/

@Component
public class UserThreadLocal {

    private UserThreadLocal(){}

    private static ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();

    public static void set(SysUser user){
        threadLocal.set(user);
    }

    public static SysUser get(){
        return threadLocal.get();
    }

    public static void remove(){
        threadLocal.remove();
    }


}
