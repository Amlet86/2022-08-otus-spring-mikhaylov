package ru.amlet.listener;

import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.amlet.entity.Genre;
import ru.amlet.exception.GenreException;
import ru.amlet.repositories.BookRepository;
import ru.amlet.repositories.GenreRepository;

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
        super.onBeforeDelete(event);
        val source = event.getSource();
        val id = source.get("_id").toString();
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new GenreException("Genre id: " + id + "doesn't exist."));
        if (!bookRepository.findByGenre(genre).isEmpty()) {
            throw new GenreException("Can't delete Genre id: " + id + " because exist book(s) with this Genre.");
        }
    }
}
