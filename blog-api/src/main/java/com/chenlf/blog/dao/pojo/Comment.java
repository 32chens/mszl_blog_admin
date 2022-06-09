package com.chenlf.blog.dao.pojo;

import lombok.Data;

/**
 * 
 * @author ChenLF
 * @date 2022/05/26 20:22
 **/

@Data
public class Comment {

    private Long id;

    private String content;

    private Long createDate;

    private Long articleId;

    private Long authorId;

    private Long parentId;

    private Long toUid;

    private Integer level;
}
