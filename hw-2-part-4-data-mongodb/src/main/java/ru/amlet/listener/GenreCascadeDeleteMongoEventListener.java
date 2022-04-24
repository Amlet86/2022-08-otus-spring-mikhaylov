package ru.amlet.listener;

import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.amlet.entity.Genre;
import ru.amlet.exception.GenreException;
import ru.amlet.repositories.BookRepository;
import ru.amlet.repositories.GenreRepository;

import java.util.ArrayList;

@Component
public class GenreCascadeDeleteMongoEventListener extends AbstractMongoEventListener<Genre> {

    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    public GenreCascadeDeleteMongoEventListener(GenreRepository genreRepository, BookRepository bookRepository) {
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Genre> event) {
        Document document = (Document) event.getSource().get("_id");
        ArrayList list = (ArrayList) document.get("$in");
        String id = list.get(0).toString();
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new GenreException("Genre id: " + id + "doesn't exist."));
        if (!bookRepository.findByGenre(genre).isEmpty()) {
            throw new GenreException("Can't delete Genre id: " + id + " because exist book(s) with this Genre.");
        }
    }
}
