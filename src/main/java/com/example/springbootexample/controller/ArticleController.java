package com.example.springbootexample.controller;

import com.example.springbootexample.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import com.example.springbootexample.service.ArticleService;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseEntity<Article> create(@RequestBody Article article) {
        Article created = articleService.createArticle(article);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public Article get(@PathVariable Long id) {
        return articleService.getArticle(id);
    }

    @GetMapping
    public List<Article> list() {
        return articleService.getAllArticles();
    }

    @GetMapping("/category/{category}")
    public List<Article> getArticlesByCategory(@PathVariable String category) {
        return articleService.getArticlesByCategory(category);
    }

    @GetMapping("/tag/{tag}")
    public List<Article> getArticlesByTag(@PathVariable String tag) {
        return articleService.getArticlesByTag(tag);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Article article) {
        int result = articleService.updateArticle(article);
        if (result > 0) {
            return ResponseEntity.ok("更新成功");
        } else {
            return ResponseEntity.status(404).body("未找到该文章，更新失败");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        int result = articleService.deleteArticle(id);
        if (result > 0) {
            return ResponseEntity.ok("删除成功");
        } else {
            return ResponseEntity.status(404).body("未找到该文章，删除失败");
        }
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<?> likeArticle(@PathVariable Long id) {
        int result = articleService.incrementLikes(id);
        if (result > 0) {
            return ResponseEntity.ok("点赞成功");
        } else {
            return ResponseEntity.status(404).body("未找到该文章，点赞失败");
        }
    }

    @PostMapping("/{id}/favorite")
    public ResponseEntity<?> favoriteArticle(@PathVariable Long id) {
        int result = articleService.incrementFavorites(id);
        if (result > 0) {
            return ResponseEntity.ok("收藏成功");
        } else {
            return ResponseEntity.status(404).body("未找到该文章，收藏失败");
        }
    }

}
