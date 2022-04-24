package ru.amlet.service;

import org.springframework.stereotype.Service;
import ru.amlet.entity.Book;
import ru.amlet.entity.Comment;
import ru.amlet.exception.BookException;
import ru.amlet.repositories.BookRepository;
import ru.amlet.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    public CommentServiceImpl(CommentRepository commentRepository,
                              BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Comment create(String content, long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException(String.format("Book id: %s does not exist", bookId)));
        return commentRepository.save(new Comment(null, content, book));
    }

    @Override
    public Optional<Comment> findById(String id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> findByContent(String content) {
        return commentRepository.findByContent(content);
    }

    @Override
    public void update(String id, String content, long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException(String.format("Book id: %s does not exist", bookId)));
        commentRepository.save(new Comment(id, content, book));
    }

    @Override
    public void deleteById(String id) {
        commentRepository.deleteAllById(List.of(id));
    }

    @Override
    public long count() {
        return commentRepository.count();
    }
}
