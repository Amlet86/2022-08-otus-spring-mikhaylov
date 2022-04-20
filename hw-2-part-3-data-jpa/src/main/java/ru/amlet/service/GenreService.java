package ru.amlet.service;

import ru.amlet.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Genre createGenre(String name);

    Optional<Genre> findById(long id);

    List<Genre> findByName(String name);

    List<Genre> findAll();

    void updateGenre(long id, String name);

    void deleteGenre(long id);

    long count();

}
