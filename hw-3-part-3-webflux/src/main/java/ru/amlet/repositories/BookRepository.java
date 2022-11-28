package ru.amlet.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Genre;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    Mono<Book> findById(long id);

    Flux<Book> findByName(String name);

    Flux<Book> findAll();

    Flux<Book> findByAuthor(Author author);

    Flux<Book> findByGenre(Genre genre);

}
