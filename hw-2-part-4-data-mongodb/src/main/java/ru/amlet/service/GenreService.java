package ru.amlet.service;

import ru.amlet.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Genre createGenre(String name);

    Optional<Genre> findById(String id);

    List<Genre> findByName(String name);

    List<Genre> findAll();

    void updateGenre(String id, String name);

    void deleteGenre(String id);

    long count();

}
