package ru.amlet.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public Comment createComment(String content, long bookId) {
        Book book = bookRepository.getById(bookId)
                .orElseThrow(() -> new BookException(String.format("Book id: %s does not exist", bookId)));
        return commentRepository.saveComment(new Comment(0, content, book));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findById(long id) {
        return commentRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findByContent(String content) {
        return commentRepository.getByContent(content);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        return commentRepository.getAll();
    }

    @Override
    @Transactional
    public void updateComment(long id, String content, long bookId) {
        Book book = bookRepository.getById(bookId)
                .orElseThrow(() -> new BookException(String.format("Book id: %s does not exist", bookId)));
        commentRepository.saveComment(new Comment(id, content, book));
    }

    @Override
    @Transactional
    public void deleteComment(long id) {
        commentRepository.deleteComment(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return commentRepository.count();
    }
}
