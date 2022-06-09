package com.chenlf.blog.controller;

import com.chenlf.blog.service.CommentService;
import com.chenlf.blog.vo.Result;
import com.chenlf.blog.vo.params.CommentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 
 * @author ChenLF
 * @date 2022/05/26 21:04
 **/

@RestController
@RequestMapping("comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("article/{id}")
    public Result articleComments(@PathVariable("id") Long articleId){
        return commentService.getArticleComments(articleId);
    }

    @PostMapping("create/change")
    public Result comment(@RequestBody CommentParam commentParam){
        return commentService.comment(commentParam);
    }

}
