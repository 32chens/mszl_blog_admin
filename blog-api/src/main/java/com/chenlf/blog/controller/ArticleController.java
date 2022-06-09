package com.chenlf.blog.controller;

import com.chenlf.blog.common.aop.CacheAnnotation;
import com.chenlf.blog.common.aop.LogAnnotation;
import com.chenlf.blog.dao.dos.Archives;
import com.chenlf.blog.service.ArticleService;
import com.chenlf.blog.vo.ArticleVo;
import com.chenlf.blog.vo.Result;
import com.chenlf.blog.vo.params.ArticleParam;
import com.chenlf.blog.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 
 * @author ChenLF
 * @date 2022/05/18 21:21
 **/
@RestController
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 首页 文章列表
     *
     * @param pageParams
     */
    @PostMapping()
    @LogAnnotation(module = "文章", operation = "文章列表")
    @CacheAnnotation(expire = 5*60*1000, name = "listArticle")
    public Result listArticle(@RequestBody PageParams pageParams) {
        return articleService.listArticle(pageParams);
    }

    /**
     * 首页最热文章
     * @return
     */
    @PostMapping("hot")
    @CacheAnnotation(expire = 5*60*1000, name = "hotArticle")
    public Result hotArticle() {
        int limit = 5;
        List<ArticleVo> articles = articleService.hotArticle(limit);
        return Result.success(articles);
    }

    /**
     * 首页最新文章
     * @return
     */
    @PostMapping("new")
    @CacheAnnotation(expire = 5*60*1000, name = "newArticle")
    public Result newArticle() {
        int limit = 5;
        List<ArticleVo> articles = articleService.newArticle(limit);
        return Result.success(articles);
    }

    /**
     * 首页文章归档
     * @return
     */
    @PostMapping("listArchives")
    public Result listArchives() {
        List<Archives> archives = articleService.listArticle();
        return Result.success(archives);
    }

    /**
     * 文章详情
     * @param articleId
     * @return
     */
    @PostMapping("view/{id}")
    public Result findArticleById(@PathVariable("id") Long articleId){
        return articleService.findArticleById(articleId);
    }

    /**
     * 发布文章
     * @return
     */
    @PostMapping("publish")
    public Result publish(@RequestBody ArticleParam articleParam){
        return articleService.publish(articleParam);
    }


}
