package ru.amlet.service;

import org.springframework.security.core.Authentication;
import ru.amlet.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book createBook(String name, String authorName, String genreName, Authentication authentication);

    Optional<Book> findById(long id);

    List<Book> findByName(String name);

    List<Book> findAll();

    void updateBook(long id, String name, String authorName, String genreName);

    void deleteBook(long id);

}
