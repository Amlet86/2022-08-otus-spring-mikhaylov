package ru.amlet.listener;

import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.amlet.entity.Book;
import ru.amlet.entity.Comment;
import ru.amlet.exception.BookException;
import ru.amlet.repositories.BookRepository;
import ru.amlet.repositories.CommentRepository;

import java.util.ArrayList;
import java.util.List;

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
        Document document = (Document) event.getSource().get("_id");
        ArrayList list = (ArrayList) document.get("$in");
        String id = list.get(0).toString();
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookException("Book id: " + id + "doesn't exist."));
        List<Comment> comments = commentRepository.findByBook(book);
        comments.forEach(comment -> {
            commentRepository.deleteById(comment.getId());
        });
    }
}
