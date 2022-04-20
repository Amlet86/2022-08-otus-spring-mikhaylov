package ru.amlet.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Интерфейс BookRepository")
class BookRepositoryJpaTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Добавляет книгу в БД")
    void shouldCreateBook() {
        var author = testEntityManager.find(Author.class, 1L);
        var genre = testEntityManager.find(Genre.class, 1L);
        var expectedBook = new Book(0, "book", author, genre);
        var actualBook = bookRepository.save(expectedBook);
        assertEquals(expectedBook, actualBook);
    }

    @Test
    @DisplayName("Возвращает ожидаемую книгу по её name")
    void shouldReturnExpectedBookByName() {
        var expectedBook = testEntityManager.find(Book.class, 1L);
        var actualBook = bookRepository.findByName(expectedBook.getName());
        assertThat(actualBook).contains(expectedBook);
    }

    @Test
    @DisplayName("Возвращает ожидаемую книгу по её id")
    void shouldReturnExpectedBookById() {
        var expectedBook = testEntityManager.find(Book.class, 1L);
        var actualBook = bookRepository.findById(expectedBook.getId()).orElse(null);
        assertEquals(expectedBook, actualBook);
    }

    @Test
    @DisplayName("Возвращает список книг из БД")
    void shouldReturnExpectedBooksList() {
        var expectedBook = testEntityManager.find(Book.class, 1L);
        var actualBooksList = bookRepository.findAll();
        assertEquals(2, actualBooksList.size());
        assertThat(actualBooksList).contains(expectedBook);
    }

    @Test
    @DisplayName("Изменяет книгу в БД")
    void shouldUpdateBook() {
        var expectedBook = testEntityManager.find(Book.class, 1L);
        expectedBook.setName("UpdatedBook");
        var actualBook = bookRepository.save(expectedBook);
        assertEquals(expectedBook, actualBook);
    }

    @Test
    @DisplayName("Удаляет книгу по её id")
    void shouldDeleteBookById() {
        var actualBook = testEntityManager.find(Book.class, 1L);
        assertNotNull(actualBook);
        testEntityManager.detach(actualBook);
        bookRepository.deleteAllById(List.of(1L));
        var deletedBook = testEntityManager.find(Book.class, 1L);
        assertNull(deletedBook);
    }

    @Test
    @DisplayName("Возвращает ожидаемое количество книг из БД")
    void shouldReturnExpectedBooksCount() {
        var actualBooksCount = bookRepository.count();
        assertEquals(2, actualBooksCount);
    }
}