package ru.amlet.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.amlet.entity.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

    Flux<Author> findByName(String name);

}
