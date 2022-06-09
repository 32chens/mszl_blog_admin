package com.chenlf.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 
 * @author ChenLF
 * @date 2022/05/18 20:43
 **/
 @SpringBootApplication
 @EnableAspectJAutoProxy
public class BlogAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class, args);
    }
}
