package com.chenlf.blog.dao.dos;

import lombok.Data;

/**
 * 文章归档
 *
 * @author ChenLF
 * @date 2022/03/13 22:51
 **/
@Data
public class Archives {
    private Integer year;
    private Integer month;
    private Integer count;
}
