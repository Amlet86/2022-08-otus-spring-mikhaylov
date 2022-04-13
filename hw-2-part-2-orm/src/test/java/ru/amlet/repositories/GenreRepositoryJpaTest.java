package ru.amlet.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.amlet.entity.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(GenreRepositoryJpa.class)
@DisplayName("Имплементация класса GenreRepository")
class GenreRepositoryJpaTest {

    @Autowired
    private GenreRepositoryJpa genreRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Добавляет жанр в БД")
    void shouldCreateGenre() {
        var expectedGenre = new Genre(0, "genre");
        var actualGenre = genreRepository.saveGenre(expectedGenre);
        assertEquals(expectedGenre, actualGenre);
    }

    @Test
    @DisplayName("Возвращает ожидаемый жанр по его name")
    void shouldReturnExpectedGenreByName() {
        var expectedGenre = testEntityManager.find(Genre.class, 1L);
        var actualGenre = genreRepository.getByName(expectedGenre.getName());
        assertThat(actualGenre).contains(expectedGenre);
    }

    @Test
    @DisplayName("Возвращает ожидаемый жанр по его id")
    void shouldReturnExpectedGenreById() {
        var expectedGenre = testEntityManager.find(Genre.class, 1L);
        var actualGenre = genreRepository.getById(expectedGenre.getId()).orElse(null);
        assertEquals(expectedGenre, actualGenre);
    }

    @Test
    @DisplayName("Возвращает список жанров из БД")
    void shouldReturnExpectedGenresList() {
        var expectedGenre = testEntityManager.find(Genre.class, 1L);
        var actualGenresList = genreRepository.getAll();
        assertEquals(1, actualGenresList.size());
        assertThat(actualGenresList).contains(expectedGenre);
    }

    @Test
    @DisplayName("Изменяет жанр в БД")
    void shouldUpdateGenre() {
        var expectedGenre = testEntityManager.find(Genre.class, 1L);
        expectedGenre.setName("UpdatedGenre");
        var actualGenre = genreRepository.saveGenre(expectedGenre);
        assertEquals(expectedGenre, actualGenre);
    }

    @Test
    @DisplayName("Удаляет жанр по его id")
    void shouldDeleteGenreById() {
        var actualGenre = testEntityManager.find(Genre.class, 1L);
        assertNotNull(actualGenre);
        testEntityManager.detach(actualGenre);
        genreRepository.deleteGenre(1);
        var deletedGenre = testEntityManager.find(Genre.class, 1L);
        assertNull(deletedGenre);
    }

    @Test
    @DisplayName("Возвращает ожидаемое количество жанров из БД")
    void shouldReturnExpectedGenresCount() {
        var actualGenresCount = genreRepository.count();
        assertEquals(1, actualGenresCount);
    }
}