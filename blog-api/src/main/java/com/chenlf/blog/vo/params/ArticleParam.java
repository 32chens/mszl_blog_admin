package com.chenlf.blog.vo.params;

import com.chenlf.blog.vo.CategoryVo;
import com.chenlf.blog.vo.TagVo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

/**
 * 
 * @author ChenLF
 * @date 2022/06/07 18:49
 **/

@Data
public class ArticleParam {

    private Long id;

    private ArticleBodyParam body;

    private CategoryVo category;

    private String summary;

    private List<TagVo> tags;

    private String title;
}
