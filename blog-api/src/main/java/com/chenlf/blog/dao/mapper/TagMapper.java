package com.chenlf.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenlf.blog.dao.pojo.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
@Repository
public interface TagMapper extends BaseMapper<Tag> {
    /**
     * 根据文章id查询标签列表
     *
     * @param articleId
     * @return
     */
    List<Tag> findTagsByArticleId(@Param("articleId") Long articleId);

    /**
     * 查询 最热的标签的id
     *
     * @param limit
     * @return
     */
    List<Long> findHotsTagIds(@Param("limit") int limit);

    /**
     * 根据id查询tag
     *
     * @param tagIds
     * @return
     */
    List<Tag> findTagsByTagIds(List<Long> tagIds);
}
