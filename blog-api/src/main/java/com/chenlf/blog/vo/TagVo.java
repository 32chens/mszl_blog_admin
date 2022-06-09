package com.chenlf.blog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author ChenLF
 * @date 2022/03/13 16:04
 **/
@Data
public class TagVo {
//    @JsonSerialize(using = ToStringSerializer.class)
    private String id;

    private String tagName;

    private String avatar;
}
