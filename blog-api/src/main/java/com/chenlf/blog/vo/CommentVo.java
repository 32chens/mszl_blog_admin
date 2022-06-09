package com.chenlf.blog.vo;



import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

/**
 * 
 * @author ChenLF
 * @date 2022/05/26 21:11
 **/

@Data
public class CommentVo {

    //转换成json，防止分布式id传到前端精度损失
//    @JsonSerialize(using = ToStringSerializer.class)
    private String id;

    private UserVo author;

    private String content;

    private List<CommentVo> childrens;

    private String createDate;

    private Integer level;

    private UserVo toUser;

}
