package ru.amlet.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.amlet.entity.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Интерфейс AuthorRepository")
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Добавляет автора в БД")
    void shouldCreateAuthor() {
        var expectedAuthor = new Author(0, "author");
        var actualAuthor = authorRepository.save(expectedAuthor);
        assertEquals(expectedAuthor, actualAuthor);
    }

    @Test
    @DisplayName("Возвращает ожидаемого автора по его name")
    void shouldReturnExpectedAuthorByName() {
        var expectedAuthor = testEntityManager.find(Author.class, 1L);
        var actualAuthor = authorRepository.findByName(expectedAuthor.getName());
        assertThat(actualAuthor).contains(expectedAuthor);
    }

    @Test
    @DisplayName("Возвращает ожидаемого автора по его id")
    void shouldReturnExpectedAuthorById() {
        var expectedAuthor = testEntityManager.find(Author.class, 1L);
        var actualAuthor = authorRepository.findById(expectedAuthor.getId()).orElse(null);
        assertEquals(expectedAuthor, actualAuthor);
    }

    @Test
    @DisplayName("Возвращает список авторов из БД")
    void shouldReturnExpectedAuthorsList() {
        var expectedAuthor = testEntityManager.find(Author.class, 1L);
        var actualAuthorsList = authorRepository.findAll();
        assertEquals(1, actualAuthorsList.size());
        assertThat(actualAuthorsList).contains(expectedAuthor);
    }

    @Test
    @DisplayName("Изменяет автора в БД")
    void shouldUpdateAuthor() {
        var expectedAuthor = testEntityManager.find(Author.class, 1L);
        expectedAuthor.setName("UpdatedAuthor");
        var actualAuthor = authorRepository.save(expectedAuthor);
        assertEquals(expectedAuthor, actualAuthor);
    }

    @Test
    @DisplayName("Удаляет автора по его id")
    void shouldDeleteAuthorById() {
        var actualAuthor = testEntityManager.find(Author.class, 1L);
        assertNotNull(actualAuthor);
        testEntityManager.detach(actualAuthor);
        authorRepository.deleteAllById(List.of(1L));
        var deletedAuthor = testEntityManager.find(Author.class, 1L);
        assertNull(deletedAuthor);
    }

    @Test
    @DisplayName("Возвращает ожидаемое количество авторов из БД")
    void shouldReturnExpectedAuthorsCount() {
        var actualAuthorsCount = authorRepository.count();
        assertEquals(1, actualAuthorsCount);
    }
}