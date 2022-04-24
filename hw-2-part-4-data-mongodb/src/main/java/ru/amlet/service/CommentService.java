package ru.amlet.service;

import ru.amlet.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Comment create(String content, long bookId);

    Optional<Comment> findById(String id);

    List<Comment> findByContent(String content);

    void update(String id, String content, long bookId);

    void deleteById(String id);

    long count();
}
