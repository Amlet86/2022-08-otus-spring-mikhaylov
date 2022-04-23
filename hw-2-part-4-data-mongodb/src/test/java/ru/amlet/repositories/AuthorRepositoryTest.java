package ru.amlet.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.amlet.entity.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@DisplayName("Интерфейс AuthorRepository")
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    @DisplayName("Добавляет автора в БД")
    void shouldCreateAuthor() {
        var expectedAuthor = new Author("firstAuthor");
        var actualAuthor = authorRepository.save(expectedAuthor);
        assertEquals(expectedAuthor, actualAuthor);
    }

    @Test
    @DisplayName("Возвращает ожидаемого автора по его name")
    void shouldReturnExpectedAuthorByName() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("firstAuthor"));
        var expectedAuthor = mongoTemplate.findOne(query, Author.class);
        var actualAuthor = authorRepository.findByName(expectedAuthor.getName());
        assertThat(actualAuthor).contains(expectedAuthor);
    }

    @Test
    @DisplayName("Возвращает ожидаемого автора по его id")
    void shouldReturnExpectedAuthorById() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("firstAuthor"));
        var expectedAuthor = mongoTemplate.findOne(query, Author.class);
        var actualAuthor = authorRepository.findById(expectedAuthor.getId()).orElse(null);
        assertEquals(expectedAuthor, actualAuthor);
    }


    @Test
    @DisplayName("Возвращает список авторов из БД")
    void shouldReturnExpectedAuthorsList() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").exists(true));
        var expectedAuthor = mongoTemplate.find(query, Author.class);
        var actualAuthorsList = authorRepository.findAll();
        assertEquals(2, actualAuthorsList.size());
        assertThat(actualAuthorsList).hasSameElementsAs(expectedAuthor);
    }

    @Test
    @DisplayName("Изменяет автора в БД")
    void shouldUpdateAuthor() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("firstAuthor"));
        var expectedAuthor = mongoTemplate.findOne(query, Author.class);
        expectedAuthor.setName("updatedAuthor");
        var actualAuthor = authorRepository.save(expectedAuthor);
        assertEquals(expectedAuthor, actualAuthor);
    }

    @Test
    @DisplayName("Удаляет автора по его id")
    void shouldDeleteAuthorById() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("secondAuthor"));
        var actualAuthor = mongoTemplate.findOne(query, Author.class);
        assertNotNull(actualAuthor);
        authorRepository.deleteAllById(List.of(actualAuthor.getId()));
        var deletedAuthor = mongoTemplate.findOne(query, Author.class);
        assertNull(deletedAuthor);
    }

}