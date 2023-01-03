package ru.amlet.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Genre;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
class BookRepositoryTest {

    private final Author firstAuthor = new Author("1", "firstAuthor");
    private final Author secondAuthor = new Author("2", "secondAuthor");
    private final Genre firstGenre = new Genre("1", "firstGenre");
    private final Genre secondGenre = new Genre("2", "secondGenre");
    private final Book firstBook = Book.builder().name("firstBook").author(firstAuthor).genre(firstGenre).build();
    private final Book secondBook = Book.builder().name("secondBook").author(secondAuthor).genre(secondGenre).build();
    private final Book thirdBook = Book.builder().name("thirdBook").author(firstAuthor).genre(secondGenre).build();

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void before() {
        bookRepository
                .saveAll(Arrays.asList(firstBook, secondBook, thirdBook))
                .subscribe();
    }

    @Test
    void getAll() {
        Flux<Book> bookFlux = bookRepository.findAll();
        StepVerifier
                .create(bookFlux)
                .assertNext(book -> assertEquals(firstBook, book))
                .assertNext(book -> assertEquals(secondBook, book))
                .assertNext(book -> assertEquals(thirdBook, book))
                .expectComplete()
                .verify();
    }

}
