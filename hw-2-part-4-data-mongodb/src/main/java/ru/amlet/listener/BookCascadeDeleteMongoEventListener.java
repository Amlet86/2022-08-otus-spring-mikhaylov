package ru.amlet.listener;

import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.amlet.entity.Book;
import ru.amlet.exception.BookException;
import ru.amlet.repositories.BookRepository;
import ru.amlet.repositories.CommentRepository;

@Component
public class BookCascadeDeleteMongoEventListener extends AbstractMongoEventListener<Book> {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    public BookCascadeDeleteMongoEventListener(CommentRepository commentRepository, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Book> event) {
        super.onBeforeDelete(event);
        val source = event.getSource();
        val id = source.get("_id").toString();
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookException("Book id: " + id + "doesn't exist."));
        commentRepository.deleteAllByBook(book);
    }
}
