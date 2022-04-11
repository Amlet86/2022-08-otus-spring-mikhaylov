package ru.amlet.repositories;

import org.springframework.stereotype.Repository;
import ru.amlet.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public AuthorRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Author saveAuthor(Author author) {
        if (author.getId() <= 0) {
            entityManager.persist(author);
            return author;
        } else {
            return entityManager.merge(author);
        }
    }

    @Override
    public Optional<Author> getById(long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public List<Author> getByName(String name) {
        TypedQuery<Author> query = entityManager.createQuery(
                "select a from Author a where a.name = :name",
                Author.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = entityManager.createQuery(
                "select a from Author a ",
                Author.class);
        return query.getResultList();
    }

    @Override
    public void deleteAuthor(long id) {
        Query query = entityManager.createQuery(
                "delete from Author a where a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public long count() {
        TypedQuery<Long> query = entityManager.createQuery(
                "select count(a) from Author a",
                Long.class);
        Long count = query.getSingleResult();
        return count == null ? 0 : count;
    }

}
