package ru.amlet.service;

import ru.amlet.entity.Book;

public interface BookService {

    long createBook(Book book);

     Book find(Book book);

     void updateBook(Book book);

     void deleteBook(long id);

    int count();
}
