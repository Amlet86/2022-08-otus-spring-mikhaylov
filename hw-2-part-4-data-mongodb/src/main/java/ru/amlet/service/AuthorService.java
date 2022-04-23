package ru.amlet.service;

import ru.amlet.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author createAuthor(String name);

    Optional<Author> findById(String id);

    List<Author> findByName(String name);

    List<Author> findAll();

    void updateAuthor(String id, String name);

    void deleteAuthor(String id);

    long count();
}
