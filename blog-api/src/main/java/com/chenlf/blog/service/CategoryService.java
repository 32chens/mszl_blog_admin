package com.chenlf.blog.service;

import com.chenlf.blog.vo.CategoryVo;
import com.chenlf.blog.vo.Result;

public interface CategoryService {

    /**
     * 根据类型id查找分类
     * @param categoryId
     * @return
     */
    CategoryVo findCategoryList(Long categoryId);

    /**
     * 查询所有分类
     * @return
     */
    Result findAll();

    /**
     * 查询所有分类(详细)
     * @return
     */
    Result findAllDetail();

    /**
     * 根据分类id查询分类详细信息
     * @return
     * @param id
     */
    Result findDetailByID(Long id);
}
