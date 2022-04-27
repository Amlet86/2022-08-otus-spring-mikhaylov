package ru.amlet.listener;

import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.amlet.entity.Author;
import ru.amlet.exception.AuthorException;
import ru.amlet.repositories.AuthorRepository;
import ru.amlet.repositories.BookRepository;

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
        super.onBeforeDelete(event);
        val source = event.getSource();
        val id = source.get("_id").toString();
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorException("Author id: " + id + "doesn't exist."));
        if (!bookRepository.findByAuthor(author).isEmpty()) {
            throw new AuthorException("Can't delete Author id: " + id + " because exist book(s) with this Author.");
        }
    }

}
