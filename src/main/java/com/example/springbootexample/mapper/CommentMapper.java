package com.example.springbootexample.mapper;

import com.example.springbootexample.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("INSERT INTO comment(article_id, user_id, content) VALUES(#{articleId}, #{userId}, #{content})")
    int insert(Comment comment);

    @Select("SELECT * FROM comment WHERE id = #{id}")
    Comment findById(Long id);

    @Select("SELECT * FROM comment WHERE article_id = #{articleId} ORDER BY created_at DESC")
    List<Comment> findByArticleId(Long articleId);

    @Update("UPDATE comment SET content = #{content} WHERE id = #{id}")
    int update(Comment comment);

    @Delete("DELETE FROM comment WHERE id = #{id}")
    int delete(Long id);
}