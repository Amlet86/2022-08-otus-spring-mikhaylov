package ru.amlet.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.amlet.entity.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {

    List<Author> findByName(String name);

}
