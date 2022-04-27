package ru.amlet.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.amlet.entity.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@DisplayName("Интерфейс GenreRepository")
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    @DisplayName("Добавляет жанр в БД")
    void shouldCreateGenre() {
        var expectedGenre = new Genre("firstGenre");
        var actualGenre = genreRepository.save(expectedGenre);
        assertEquals(expectedGenre, actualGenre);
    }

    @Test
    @DisplayName("Возвращает ожидаемый жанр по его name")
    void shouldReturnExpectedGenreByName() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("firstGenre"));
        var expectedGenre = mongoTemplate.findOne(query, Genre.class);
        var actualGenre = genreRepository.findByName(expectedGenre.getName());
        assertThat(actualGenre).contains(expectedGenre);
    }

    @Test
    @DisplayName("Возвращает ожидаемый жанр по его id")
    void shouldReturnExpectedGenreById() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("firstGenre"));
        var expectedGenre = mongoTemplate.findOne(query, Genre.class);
        var actualGenre = genreRepository.findById(expectedGenre.getId()).orElse(null);
        assertEquals(expectedGenre, actualGenre);
    }

    @Test
    @DisplayName("Возвращает список жанров из БД")
    void shouldReturnExpectedGenresList() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").exists(true));
        var expectedGenre = mongoTemplate.find(query, Genre.class);
        var actualGenresList = genreRepository.findAll();
        assertEquals(2, actualGenresList.size());
        assertThat(actualGenresList).hasSameElementsAs(expectedGenre);
    }

    @Test
    @DisplayName("Изменяет жанр в БД")
    void shouldUpdateGenre() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("firstGenre"));
        var expectedGenre = mongoTemplate.findOne(query, Genre.class);
        expectedGenre.setName("updatedGenre");
        var actualGenre = genreRepository.save(expectedGenre);
        assertEquals(expectedGenre, actualGenre);
    }

    @Test
    @DisplayName("Удаляет жанр по его id")
    void shouldDeleteGenreById() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("secondGenre"));
        var actualGenre = mongoTemplate.findOne(query, Genre.class);
        assertNotNull(actualGenre);
        genreRepository.deleteById(actualGenre.getId());
        var deletedGenre = mongoTemplate.findOne(query, Genre.class);
        assertNull(deletedGenre);
    }

}