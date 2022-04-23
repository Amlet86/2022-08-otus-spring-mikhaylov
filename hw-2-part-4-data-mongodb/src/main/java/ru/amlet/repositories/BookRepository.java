package ru.amlet.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.amlet.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findById(long id);

    List<Book> findByName(String name);

    List<Book> findAll();

}
