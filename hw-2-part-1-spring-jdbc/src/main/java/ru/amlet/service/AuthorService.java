package ru.amlet.service;

import ru.amlet.entity.Author;
import ru.amlet.entity.Book;

import java.util.List;

public interface AuthorService {

    long createAuthor(Author author);

    Author find(Author author);

    void updateAuthor(Author author);

    void deleteAuthor(long id);

}
