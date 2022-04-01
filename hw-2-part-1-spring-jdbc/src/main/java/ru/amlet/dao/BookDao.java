package ru.amlet.dao;

import ru.amlet.entity.Book;

import java.util.List;

public interface BookDao {

    long createBook(Book book);

    Book getByName(String name);

    Book getById(long id);

    List<Book> getAll();

    void updateBook(Book book);

    void deleteBook(long id);

    int count();
}
