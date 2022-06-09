package com.chenlf.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenlf.blog.dao.pojo.Category;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public interface CategoryMapper extends BaseMapper<Category> {
}
