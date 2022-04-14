package ru.amlet.repositories;

import org.springframework.stereotype.Repository;
import ru.amlet.entity.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public GenreRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Genre saveGenre(Genre genre) {
        if (genre.getId() <= 0) {
            entityManager.persist(genre);
            return genre;
        } else {
            return entityManager.merge(genre);
        }
    }

    @Override
    public Optional<Genre> getById(long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    @Override
    public List<Genre> getByName(String name) {
        TypedQuery<Genre> query = entityManager.createQuery(
                "select g from Genre g where g.name = :name",
                Genre.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = entityManager.createQuery(
                "select g from Genre g",
                Genre.class);
        return query.getResultList();
    }

    @Override
    public void deleteGenre(long id) {
        Query query = entityManager.createQuery(
                "delete from Genre g where g.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public long count() {
        TypedQuery<Long> query = entityManager.createQuery(
                "select count(g) from Genre g",
                Long.class);
        Long count = query.getSingleResult();
        return count == null ? 0 : count;
    }

}
