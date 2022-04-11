package ru.amlet.repositories;

import ru.amlet.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Author saveAuthor(Author author);

    Optional<Author> getById(long id);

    List<Author> getByName(String name);

    List<Author> getAll();

    void deleteAuthor(long id);

    long count();

}
