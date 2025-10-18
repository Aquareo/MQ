package com.example.springbootexample.service.impl;

import com.example.springbootexample.entity.Article;
import com.example.springbootexample.mapper.ArticleMapper;
import com.example.springbootexample.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ArticleServiceImpl implements  ArticleService{

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Article createArticle(Article article)
    {   
        LocalDateTime now = LocalDateTime.now();
        article.setCreatedAt(now);  // 设置创建时间
        article.setUpdatedAt(now);  // 设置更新时间
        articleMapper.insert(article);
        return article;
    }


    @Override
    public Article getArticle(Long id)
    {
        return articleMapper.findById(id);
    }

    @Override
    public List<Article> getAllArticles()
    {
        return articleMapper.findAll();
    }

    @Override
    public int updateArticle(Article article)
    {
        // 新增：更新时刷新更新时间
        article.setUpdatedAt(LocalDateTime.now());
        return articleMapper.update(article);
    }

    @Override
    public int deleteArticle(Long id)
    {
        return articleMapper.delete(id);
    }

    @Override
    public List<Article> getArticlesByCategory(String category) {
        return articleMapper.findByCategory(category);
    }

    @Override
    public List<Article> getArticlesByTag(String tag) {
        return articleMapper.findByTag(tag);
    }

    @Override
    public int incrementLikes(Long id) {
        return articleMapper.incrementLikes(id);
    }

    @Override
    public int incrementFavorites(Long id) {
        return articleMapper.incrementFavorites(id);
    }


}
