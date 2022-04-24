package ru.amlet.service;

import ru.amlet.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author create(String name);

    Optional<Author> findById(String id);

    List<Author> findByName(String name);

    List<Author> findAll();

    void update(String id, String name);

    void deleteById(String id);

    long count();
}
