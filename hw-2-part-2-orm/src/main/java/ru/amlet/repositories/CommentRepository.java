package ru.amlet.repositories;

import ru.amlet.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment saveComment(Comment comment);

    Optional<Comment> getById(long id);

    List<Comment> getByContent(String content);

    List<Comment> getAll();

    void deleteComment(long id);

    long count();

}
