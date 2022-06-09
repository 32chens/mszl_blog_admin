package com.chenlf.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chenlf.blog.dao.mapper.ArticleMapper;
import com.chenlf.blog.dao.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ChenLF
 * @date 2022/05/26 19:54
 **/

@Component
public class ThreadService {

    @Autowired
    private ArticleMapper articleMapper;

    @Async("taskExcutor")
    public void updateViewCount(Article article){
        int viewCounts = article.getViewCounts();
        Article updateArticle = new Article();
        updateArticle.setViewCounts(viewCounts + 1);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getViewCounts,viewCounts);
        queryWrapper.eq(Article::getId,article.getId());
        articleMapper.update(updateArticle, queryWrapper);
    }

}
