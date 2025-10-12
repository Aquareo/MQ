package com.example.springbootexample.mapper;

import com.example.springbootexample.entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("INSERT INTO article(title, content, author_id) VALUES(#{title}, #{content}, #{authorId})")
    int insert(Article article);

    @Select("SELECT * FROM article WHERE id = #{id}")
    Article findById(Long id);

    @Select("SELECT * FROM article ORDER BY created_at DESC")
    List<Article> findAll();

    @Update("UPDATE article SET title = #{title}, content = #{content} WHERE id = #{id}")
    int update(Article article);

    @Delete("DELETE FROM article WHERE id = #{id}")
    int delete(Long id);
}
