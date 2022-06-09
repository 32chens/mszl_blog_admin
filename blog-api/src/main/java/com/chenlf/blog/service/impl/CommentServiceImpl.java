package com.chenlf.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chenlf.blog.dao.mapper.CommentMapper;
import com.chenlf.blog.dao.pojo.Comment;
import com.chenlf.blog.dao.pojo.SysUser;
import com.chenlf.blog.service.CommentService;
import com.chenlf.blog.service.SysUserService;
import com.chenlf.blog.utils.UserThreadLocal;
import com.chenlf.blog.vo.CommentVo;
import com.chenlf.blog.vo.Result;
import com.chenlf.blog.vo.UserVo;
import com.chenlf.blog.vo.params.CommentParam;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author ChenLF
 * @date 2022/05/26 21:07
 **/

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Result getArticleComments(Long articleId) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId,articleId);
        queryWrapper.eq(Comment::getLevel,1);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        List<CommentVo> commentVos = copyList(comments);
        return Result.success(commentVos);
    }

    @Override
    public Result comment(CommentParam commentParam) {
        SysUser sysUser = UserThreadLocal.get();
        Comment comment = new Comment();
        comment.setArticleId(commentParam.getArticleId());
        comment.setAuthorId(sysUser.getId());
        comment.setContent(commentParam.getContent());
        comment.setCreateDate(System.currentTimeMillis());
        Long parent = commentParam.getParent();
        if (parent == null || parent == 0) {
            comment.setLevel(1);
        }else{
            comment.setLevel(2);
        }
        comment.setParentId(parent == null ? 0 : parent);
        Long toUserId = commentParam.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);
        this.commentMapper.insert(comment);
        return Result.success(null);
    }

    private List<CommentVo> copyList(List<Comment> comments) {
        List<CommentVo> commentVos = new ArrayList<>();

        for (Comment comment : comments) {
            CommentVo commentVo = copy(comment);
            commentVos.add(commentVo);
        }
        return commentVos;
    }

    private CommentVo copy(Comment comment) {
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment,commentVo);
        commentVo.setId(String.valueOf(comment.getId()));
        commentVo.setCreateDate(new DateTime(comment.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        SysUser author = sysUserService.findUserById(comment.getAuthorId());
        UserVo authorvo = new UserVo();
        BeanUtils.copyProperties(author,authorvo);
        commentVo.setAuthor(authorvo);

        List<CommentVo> commentVoList = findCommentsByParentId(comment.getId());
        commentVo.setChildrens(commentVoList);

        if (comment.getLevel() > 1) {
            SysUser toUser = sysUserService.findUserById(comment.getToUid());
            UserVo touservo = new UserVo();
            BeanUtils.copyProperties(toUser,touservo);
            commentVo.setToUser(touservo);
        }


        return commentVo;
    }

    private List<CommentVo> findCommentsByParentId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId,id);
        queryWrapper.eq(Comment::getLevel,2);
        List<Comment> comments = this.commentMapper.selectList(queryWrapper);
        return copyList(comments);
    }
}
