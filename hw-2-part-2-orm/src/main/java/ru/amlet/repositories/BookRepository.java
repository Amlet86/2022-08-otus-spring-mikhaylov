package ru.amlet.repositories;

import ru.amlet.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book saveBook(Book book);

    List<Book> getByName(String name);

    Optional<Book> getById(long id);

    List<Book> getAll();

    void deleteBook(long id);

    long count();
}
