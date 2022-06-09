package com.chenlf.blog.vo.params;

import lombok.Data;

/**
 * 
 * @author ChenLF
 * @date 2022/05/18 21:23
 **/

@Data
public class PageParams {

    private int page = 1;

    private int pageSize = 10;

    private Long categoryId;

    private Long tagId;

    private String year;

    private String month;

    public String getMonth(){
        if (this.month != null && this.month.length() == 1){
            return "0"+this.month;
        }
        return this.month;
    }
}
