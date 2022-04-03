package ru.amlet.service;

import ru.amlet.entity.Book;

import java.util.List;

public interface BookService {

    long createBook(String name, String authorName, String genreName);

    Book findById(long id);

    Book findByName(String name);

    List<Book> findAll();

    void updateBook(long id, String name, String authorName, String genreName);

    void deleteBook(long id);

    int count();
}
