package ru.amlet.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.amlet.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    String GRAPH_NAME = "book-entity-graph";

    @EntityGraph(GRAPH_NAME)
    Optional<Book> findById(long id);

    @EntityGraph(GRAPH_NAME)
    List<Book> findByName(String name);

    @EntityGraph(GRAPH_NAME)
    List<Book> findAll();

}
