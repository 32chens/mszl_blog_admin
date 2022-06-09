package com.chenlf.blog.dao.pojo;

import lombok.Data;

/**
 * 
 * @author ChenLF
 * @date 2022/06/07 18:54
 **/

@Data
public class ArticleTag {

    private Long id;

    private Long articleId;

    private Long tagId;
}

