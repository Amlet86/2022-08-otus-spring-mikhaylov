package ru.amlet.service;

import ru.amlet.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Comment createComment(String content, long bookId);

    Optional<Comment> findById(String id);

    List<Comment> findByContent(String content);

    void updateComment(String id, String content, long bookId);

    void deleteComment(String id);

    long count();
}
