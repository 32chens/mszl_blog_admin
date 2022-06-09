package com.chenlf.blog.controller;

import com.chenlf.blog.dao.pojo.Tag;
import com.chenlf.blog.service.TagService;
import com.chenlf.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 
 * @author ChenLF
 * @date 2022/05/19 20:53
 **/

@RestController
@RequestMapping("tags")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 最热标签
     * @return
     */
    @GetMapping("hot")
    public Result hot() {
        int limit = 6;
        return tagService.hots(limit);
    }

    /**
     * 获取所有标签
     * @return
     */
    @GetMapping
    public Result findAll(){
        return tagService.findAll();
    }

    /**
     * 获取所有标签(详细)
     * @return
     */
    @GetMapping("detail")
    public Result findAllDetail(){
        return tagService.findAllDetail();
    }

    /**
     * 根据标签id查询标签(详细)
     * @return
     */
    @GetMapping("detail/{id}")
    public Result findDetailById(@PathVariable("id") Long id){
        return tagService.findDetailById(id);
    }

}
