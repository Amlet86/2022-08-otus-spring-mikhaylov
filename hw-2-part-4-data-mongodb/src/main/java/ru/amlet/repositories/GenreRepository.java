package ru.amlet.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.amlet.entity.Genre;

import java.util.List;

public interface GenreRepository extends MongoRepository<Genre, String> {

    List<Genre> findByName(String name);

}
