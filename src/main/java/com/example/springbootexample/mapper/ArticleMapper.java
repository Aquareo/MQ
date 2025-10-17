package com.example.springbootexample.mapper;

import com.example.springbootexample.entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("INSERT INTO article(title, content, author_id) VALUES(#{title}, #{content}, #{authorId})")
    //@Options(useGeneratedKeys = true, keyProperty = "id")
        //如果的 ArticleMapper.insert 没有配置 useGeneratedKeys，因此插入后 article.getId() 可能还是 null
    int insert(Article article);

    @Select("SELECT * FROM article WHERE id = #{id}")
    Article findById(Long id);

    @Select("SELECT * FROM article ORDER BY created_at DESC")
    List<Article> findAll();

    @Update("UPDATE article SET title = #{title}, content = #{content} WHERE id = #{id}")
    int update(Article article);

    @Delete("DELETE FROM article WHERE id = #{id}")
    int delete(Long id);

    @Select("SELECT * FROM article WHERE category = #{category}")
    List<Article> findByCategory(String category);

    @Select("SELECT * FROM article WHERE tags LIKE CONCAT('%', #{tag}, '%')")
    List<Article> findByTag(String tag);

    @Update("UPDATE article SET likes = likes + 1 WHERE id = #{id}")
    int incrementLikes(Long id);

    @Update("UPDATE article SET favorites = favorites + 1 WHERE id = #{id}")
    int incrementFavorites(Long id);
}
