package ru.amlet.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@DisplayName("Интерфейс BookRepository")
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    @DisplayName("Добавляет книгу в БД")
    void shouldCreateBook() {
        Query authorQuery = new Query();
        authorQuery.addCriteria(Criteria.where("name").is("firstAuthor"));
        var author = mongoTemplate.findOne(authorQuery, Author.class);
        Query genreQuery = new Query();
        genreQuery.addCriteria(Criteria.where("name").is("firstGenre"));
        var genre = mongoTemplate.findOne(genreQuery, Genre.class);
        var expectedBook = new Book(null, "book", author, genre);
        var actualBook = bookRepository.save(expectedBook);
        assertEquals(expectedBook, actualBook);
    }

    @Test
    @DisplayName("Возвращает ожидаемую книгу по её name")
    void shouldReturnExpectedBookByName() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("thirdBook"));
        var expectedBook = mongoTemplate.findOne(query, Book.class);
        var actualBook = bookRepository.findByName(expectedBook.getName());
        assertThat(actualBook).contains(expectedBook);
    }

    @Test
    @DisplayName("Возвращает ожидаемую книгу по её id")
    void shouldReturnExpectedBookById() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("thirdBook"));
        var expectedBook = mongoTemplate.findOne(query, Book.class);
        var actualBook = bookRepository.findById(expectedBook.getId()).orElse(null);
        assertEquals(expectedBook, actualBook);
    }

    @Test
    @DisplayName("Возвращает список книг из БД")
    void shouldReturnExpectedBooksList() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").exists(true));
        var expectedBook = mongoTemplate.find(query, Book.class);
        var actualBooksList = bookRepository.findAll();
        assertEquals(3, actualBooksList.size());
        assertThat(actualBooksList).hasSameElementsAs(expectedBook);
    }

    @Test
    @DisplayName("Изменяет книгу в БД")
    void shouldUpdateBook() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("firstBook"));
        var expectedBook = mongoTemplate.findOne(query, Book.class);
        expectedBook.setName("updatedBook");
        var actualBook = bookRepository.save(expectedBook);
        assertEquals(expectedBook, actualBook);
    }

    @Test
    @DisplayName("Удаляет книгу по её id")
    void shouldDeleteBookById() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("secondBook"));
        var actualBook = mongoTemplate.findOne(query, Book.class);
        assertNotNull(actualBook);
        bookRepository.deleteAllById(List.of(actualBook.getId()));
        var deletedBook = mongoTemplate.findOne(query, Book.class);
        assertNull(deletedBook);
    }

}