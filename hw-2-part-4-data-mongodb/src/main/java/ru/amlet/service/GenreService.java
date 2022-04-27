package ru.amlet.service;

import ru.amlet.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Genre create(String name);

    Optional<Genre> findById(String id);

    List<Genre> findByName(String name);

    List<Genre> findAll();

    void update(String id, String name);

    void deleteById(String id);

    long count();

}
