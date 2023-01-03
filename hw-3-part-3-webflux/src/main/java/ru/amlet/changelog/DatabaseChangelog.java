package ru.amlet.changelog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Genre;
import ru.amlet.repositories.AuthorRepository;
import ru.amlet.repositories.BookRepository;
import ru.amlet.repositories.GenreRepository;

@Component
@RequiredArgsConstructor
public class DatabaseChangelog {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    private final Author firstAuthor = new Author("1", "firstAuthor");
    private final Author secondAuthor = new Author("2", "secondAuthor");
    private final Genre firstGenre = new Genre("1", "firstGenre");
    private final Genre secondGenre = new Genre("2", "secondGenre");
    private final Book firstBook = Book.builder().name("firstBook").author(firstAuthor).genre(firstGenre).build();
    private final Book secondBook = Book.builder().name("secondBook").author(secondAuthor).genre(secondGenre).build();
    private final Book thirdBook = Book.builder().name("thirdBook").author(firstAuthor).genre(secondGenre).build();

    public void insertEntities() {
        insertAuthors(authorRepository);
        insertGenres(genreRepository);
        insertBooks(bookRepository);
    }

    private void insertAuthors(AuthorRepository repository) {
        repository.save(firstAuthor).subscribe();
        repository.save(secondAuthor).subscribe();
    }

    private void insertGenres(GenreRepository repository) {
        repository.save(firstGenre).subscribe();
        repository.save(secondGenre).subscribe();
    }

    private void insertBooks(BookRepository bookRepository) {
        bookRepository.save(firstBook).subscribe();
        bookRepository.save(secondBook).subscribe();
        bookRepository.save(thirdBook).subscribe();
    }

}
