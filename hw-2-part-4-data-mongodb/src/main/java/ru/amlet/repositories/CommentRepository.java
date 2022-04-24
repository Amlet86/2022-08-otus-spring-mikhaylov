package ru.amlet.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.amlet.entity.Book;
import ru.amlet.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, String> {

    Optional<Comment> findById(long id);

    List<Comment> findByContent(String content);

    List<Comment> findByBook(Book book);

}
