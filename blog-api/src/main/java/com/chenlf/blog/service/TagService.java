package com.chenlf.blog.service;

import com.chenlf.blog.vo.Result;
import com.chenlf.blog.vo.TagVo;

import java.util.List;

/**
 * @author ChenLF
 * @date 2022/03/13 16:47
 **/
public interface TagService {
    /**
     * 根據文章id查詢標簽列表
     *
     * @param articleId
     * @return
     */
    List<TagVo> findTagsByArticleId(Long articleId);

    /**
     * 最热标签
     * @param limit
     * @return
     */
    Result hots(int limit);

    /**
     * 查询所有标签
     * @return
     */
    Result findAll();

    /**
     * 查询所有标签(详细)
     * @return
     */
    Result findAllDetail();

    /**
     * 根据id查询详细标签
     * @param id
     * @return
     */
    Result findDetailById(Long id);
}
