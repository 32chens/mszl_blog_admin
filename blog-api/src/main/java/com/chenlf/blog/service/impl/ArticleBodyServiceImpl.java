package com.chenlf.blog.service.impl;

import com.chenlf.blog.dao.mapper.ArticleBodyMapper;
import com.chenlf.blog.dao.pojo.ArticleBody;
import com.chenlf.blog.service.ArticleBodyService;
import com.chenlf.blog.vo.ArticleBodyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author ChenLF
 * @date 2022/05/24 22:02
 **/

@Service
public class ArticleBodyServiceImpl implements ArticleBodyService {
    @Autowired
    private ArticleBodyMapper articleBodyMapper;

    @Override
    public ArticleBodyVo findArticleBodyById(Long bodyId) {
        ArticleBody articleBody = articleBodyMapper.selectById(bodyId);
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        articleBodyVo.setContent(articleBody.getContent());
        return articleBodyVo;
    }
}
