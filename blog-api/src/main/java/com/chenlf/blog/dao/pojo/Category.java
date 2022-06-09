package com.chenlf.blog.dao.pojo;

import lombok.Data;

/**
 * 
 * @author ChenLF
 * @date 2022/05/24 21:48
 **/

@Data
public class Category {

    private Long id;

    private String avatar;

    private String categoryName;

    private String description;
}