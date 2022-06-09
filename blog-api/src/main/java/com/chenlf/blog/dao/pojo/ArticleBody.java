package com.chenlf.blog.dao.pojo;

import lombok.Data;

/**
 * 
 * @author ChenLF
 * @date 2022/05/24 21:45
 **/

@Data
public class ArticleBody {

    private Long id;

    //md格式内容
    private String content;

    //html格式内容
    private String contentHtml;

    private Long articleId;
}