package ru.amlet.listener;

import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.amlet.entity.Author;
import ru.amlet.exception.AuthorException;
import ru.amlet.repositories.AuthorRepository;
import ru.amlet.repositories.BookRepository;

import java.util.ArrayList;

@Component
public class AuthorCascadeDeleteMongoEventListener extends AbstractMongoEventListener<Author> {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorCascadeDeleteMongoEventListener(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Author> event) {
        Document document = (Document) event.getSource().get("_id");
        ArrayList list = (ArrayList) document.get("$in");
        String id = list.get(0).toString();
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorException("Author id: " + id + "doesn't exist."));
        if (!bookRepository.findByAuthor(author).isEmpty()) {
            throw new AuthorException("Can't delete Author id: " + id + " because exist book(s) with this Author.");
        }
    }

}
