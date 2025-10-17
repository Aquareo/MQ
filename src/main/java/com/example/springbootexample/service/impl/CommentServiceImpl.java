package com.example.springbootexample.service.impl;

import com.example.springbootexample.entity.Comment;
import com.example.springbootexample.mapper.CommentMapper;
import com.example.springbootexample.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Comment createComment(Comment comment) {
        commentMapper.insert(comment);
        return comment;
    }

    @Override
    public Comment getComment(Long id) {
        return commentMapper.findById(id);
    }

    @Override
    public List<Comment> getCommentsByArticleId(Long articleId) {
        return commentMapper.findByArticleId(articleId);
    }

    @Override
    public int updateComment(Comment comment) {
        return commentMapper.update(comment);
    }

    @Override
    public int deleteComment(Long id) {
        return commentMapper.delete(id);
    }
}