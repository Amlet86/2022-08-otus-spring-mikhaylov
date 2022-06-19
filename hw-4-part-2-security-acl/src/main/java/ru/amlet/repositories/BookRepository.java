package ru.amlet.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.amlet.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    String GRAPH_NAME = "book-entity-graph";

    @EntityGraph(GRAPH_NAME)
    Optional<Book> findById(long id);

    @EntityGraph(GRAPH_NAME)
    @PostFilter("hasPermission(filterObject, 'READ') or hasPermission(filterObject, 'ADMINISTRATION')")
    List<Book> findByName(String name);

    @EntityGraph(GRAPH_NAME)
    @PostFilter("hasPermission(filterObject, 'READ') or hasPermission(filterObject, 'ADMINISTRATION')")
    List<Book> findAll();

}
