package ru.amlet.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Genre;
import ru.amlet.exception.BookException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(BookDaoJdbc.class)
@DisplayName("Имплементация класса BookDao")
class BookDaoJdbcTest {

    public final static int EXPECTED_BOOKS_COUNT = 1;
    @Autowired
    private BookDaoJdbc dao;

    @Test
    @DisplayName("Добавляет книгу в БД")
    void shouldCreateBook() {
        var author = new Author();
        author.setId(1);
        var genre = new Genre();
        genre.setId(1);
        var expectedBook = new Book(2, "book2", author, genre);
        dao.createBook(expectedBook);
        var actualBook = dao.getById(expectedBook.getId());
        assertEquals(expectedBook, actualBook);
    }

    @Test
    @DisplayName("Возвращает ожидаемую книгу по её name")
    void shouldReturnExpectedBookByName() {
        var author = new Author();
        author.setId(1);
        var genre = new Genre();
        genre.setId(1);
        var expectedBook = new Book(1, "bookTest", author, genre);
        var actualBook = dao.getByName(expectedBook.getName());
        assertEquals(expectedBook, actualBook);
    }

    @Test
    @DisplayName("Возвращает ожидаемую книгу по её id")
    void shouldReturnExpectedBookById() {
        var author = new Author();
        author.setId(1);
        var genre = new Genre();
        genre.setId(1);
        var expectedBook = new Book(1, "bookTest", author, genre);
        var actualBook = dao.getById(expectedBook.getId());
        assertEquals(expectedBook, actualBook);
    }

    @Test
    @DisplayName("Возвращает список книг из БД")
    void shouldReturnExpectedBooksList() {
        var author = new Author();
        author.setId(1);
        var genre = new Genre();
        genre.setId(1);
        var expectedBook = List.of(new Book(1, "bookTest", author, genre));
        var actualBooksList = dao.getAll();
        assertThat(actualBooksList).hasSameElementsAs(expectedBook);
    }

    @Test
    @DisplayName("Изменяет книгу в БД")
    void shouldUpdateBook() {
        var author = new Author();
        author.setId(1);
        var genre = new Genre();
        genre.setId(1);
        var expectedBook = new Book(1, "bookUpdated", author, genre);
        dao.updateBook(expectedBook);
        var actualBook = dao.getById(expectedBook.getId());
        assertEquals(expectedBook, actualBook);
    }

    @Test
    @DisplayName("Удаляет книгу по её id")
    void shouldDeleteBookById() {
        var actualBook = dao.getById(1);
        assertNotNull(actualBook);
        dao.deleteBook(1);
        assertThrows(BookException.class, () -> dao.getById(1));
    }

    @Test
    @DisplayName("Возвращает ожидаемое количество книг из БД")
    void shouldReturnExpectedBooksCount() {
        var actualBooksCount = dao.count();
        assertEquals(EXPECTED_BOOKS_COUNT, actualBooksCount);
    }
}