package com.chenlf.blog.service;

import com.chenlf.blog.vo.Result;
import com.chenlf.blog.vo.params.CommentParam;

public interface CommentService {
    /**
     * 根据文章id获取评论
     * @param articleId
     * @return
     */
    Result getArticleComments(Long articleId);


    /**
     * 评论功能
     * @param commentParam
     * @return
     */
    Result comment(CommentParam commentParam);
}
