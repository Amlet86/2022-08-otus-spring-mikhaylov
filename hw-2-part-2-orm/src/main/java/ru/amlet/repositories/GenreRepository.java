package ru.amlet.repositories;

import ru.amlet.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    Genre saveGenre(Genre genre);

    Optional<Genre> getById(long id);

    List<Genre> getByName(String name);

    List<Genre> getAll();

    void deleteGenre(long id);

    long count();

}
