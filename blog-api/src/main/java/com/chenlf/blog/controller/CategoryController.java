package com.chenlf.blog.controller;

import com.chenlf.blog.service.CategoryService;
import com.chenlf.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author ChenLF
 * @date 2022/06/06 16:32
 **/

@RestController
@RequestMapping("categorys")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Result listCategory(){
        return categoryService.findAll();
    }
    @GetMapping("detail")
    public Result findAllDetail(){
        return categoryService.findAllDetail();
    }

    @GetMapping("detail/{id}")
    public Result findDetailByID(@PathVariable("id") Long id){
        return categoryService.findDetailByID(id);
    }

}
