package ru.amlet.repositories;

import org.springframework.stereotype.Repository;
import ru.amlet.entity.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private final EntityManager entityManager;
    private final static String GRAPH_NAME = "book-entity-graph";

    public BookRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Book saveBook(Book book) {
        if (book.getId() <= 0) {
            entityManager.persist(book);
            return book;
        } else {
            return entityManager.merge(book);
        }
    }

    @Override
    public Optional<Book> getById(long id) {
        Map<String, Object> properties =
                Map.of("javax.persistence.fetchgraph", entityManager.getEntityGraph(GRAPH_NAME));
        return Optional.ofNullable(entityManager.find(Book.class, id, properties));
    }

    @Override
    public List<Book> getByName(String name) {
        TypedQuery<Book> query = entityManager.createQuery(
                "select b from Book b where b.name = :name",
                Book.class);
        query.setParameter("name", name);
        query.setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph(GRAPH_NAME));
        return query.getResultList();
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = entityManager.createQuery(
                "select b from Book b ",
                Book.class);
        query.setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph(GRAPH_NAME));
        return query.getResultList();
    }

    @Override
    public void deleteBook(long id) {
        Query query = entityManager.createQuery(
                "delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public long count() {
        TypedQuery<Long> query = entityManager.createQuery(
                "select count(b) from Book b",
                Long.class);
        Long count = query.getSingleResult();
        return count == null ? 0 : count;
    }

}
