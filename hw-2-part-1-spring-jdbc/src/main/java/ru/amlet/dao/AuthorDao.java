package ru.amlet.dao;

import ru.amlet.entity.Author;

import java.util.List;

public interface AuthorDao {

    long createAuthor(Author author);

    Author getByName(String name);

    Author getById(long id);

    List<Author> getAll();

    void updateAuthor(Author author);

    void deleteAuthor(long id);

}
