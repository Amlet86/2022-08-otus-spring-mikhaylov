package ru.amlet.service;

import org.springframework.security.core.Authentication;
import ru.amlet.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author createAuthor(String name, Authentication authentication);

    Optional<Author> findById(long id);

    List<Author> findByName(String name);

    List<Author> findAll();

    void updateAuthor(long id, String name);

    void deleteAuthor(long id);

}
