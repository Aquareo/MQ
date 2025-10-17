package com.example.springbootexample.service;

import com.example.springbootexample.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Comment comment);
    Comment getComment(Long id);
    List<Comment> getCommentsByArticleId(Long articleId);
    int updateComment(Comment comment);
    int deleteComment(Long id);
}