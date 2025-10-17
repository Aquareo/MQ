package com.example.springbootexample.service;

import com.example.springbootexample.entity.Article;

import java.util.List;

public interface ArticleService {
    Article createArticle(Article article);
    Article getArticle(Long id);
    List<Article> getAllArticles();
    int updateArticle(Article article);
    int deleteArticle(Long id);
    List<Article> getArticlesByCategory(String category);
    List<Article> getArticlesByTag(String tag);
    int incrementLikes(Long id);
    int incrementFavorites(Long id);
}
