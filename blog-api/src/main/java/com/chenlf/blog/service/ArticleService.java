package com.chenlf.blog.service;

import com.chenlf.blog.dao.dos.Archives;
import com.chenlf.blog.vo.ArticleVo;
import com.chenlf.blog.vo.Result;
import com.chenlf.blog.vo.params.ArticleParam;
import com.chenlf.blog.vo.params.PageParams;

import java.util.List;

/**
 * @author ChenLF
 * @date 2022/03/13 15:52
 **/
public interface ArticleService {

    /**
     * 分页查询 文章列表
     *
     * @param pageParams
     * @return
     */
    Result listArticle(PageParams pageParams);

    /**
     * 查询最热门的文章
     *
     * @return
     */
    List<ArticleVo> hotArticle(int limit);

    /**
     * 查询最新文章
     *
     * @param limit
     * @return
     */
    List<ArticleVo> newArticle(int limit);

    /**
     * 文章归档
     *
     * @return
     */
    List<Archives> listArticle();

    /**
     * 文章详情
     * @param articleId
     * @return
     */
    Result findArticleById(Long articleId);

    /**
     * 发布文章
     * @param articleParam
     * @return
     */
    Result publish(ArticleParam articleParam);
}
