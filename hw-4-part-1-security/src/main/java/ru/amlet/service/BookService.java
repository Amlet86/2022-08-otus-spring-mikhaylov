package ru.amlet.service;

import ru.amlet.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book createBook(String name, String authorName, String genreName);

    Optional<Book> findById(long id);

    List<Book> findByName(String name);

    List<Book> findAll();

    void updateBook(long id, String name, String authorName, String genreName);

    void deleteBook(long id);

    long count();
}
