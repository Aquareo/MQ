package com.example.springbootexample.service;

import com.example.springbootexample.entity.Article;

import java.util.List;

public interface ArticleService {
    void createArticle(Article article);
    Article getArticle(Long id);
    List<Article> getAllArticles();
    int updateArticle(Article article);
    int deleteArticle(Long id);
}
