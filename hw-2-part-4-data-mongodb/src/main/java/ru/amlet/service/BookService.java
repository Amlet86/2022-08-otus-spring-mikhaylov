package ru.amlet.service;

import ru.amlet.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book createBook(String name, String authorName, String genreName);

    Optional<Book> findById(String id);

    List<Book> findByName(String name);

    List<Book> findAll();

    void updateBook(String id, String name, String authorName, String genreName);

    void deleteBook(String id);

    long count();
}
