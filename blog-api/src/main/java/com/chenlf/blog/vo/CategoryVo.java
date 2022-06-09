package com.chenlf.blog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 
 * @author ChenLF
 * @date 2022/05/24 21:47
 **/

@Data
public class CategoryVo {
//    @JsonSerialize(using = ToStringSerializer.class)
    private String id;

    private String avatar;

    private String categoryName;

    private String description;
}