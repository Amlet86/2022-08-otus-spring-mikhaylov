package ru.amlet.repositories;

import org.springframework.stereotype.Repository;
import ru.amlet.entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private final EntityManager entityManager;
    private final static String GRAPH_NAME = "comment-entity-graph";

    public CommentRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Comment saveComment(Comment comment) {
        if (comment.getId() <= 0) {
            entityManager.persist(comment);
            return comment;
        } else {
            return entityManager.merge(comment);
        }
    }

    @Override
    public Optional<Comment> getById(long id) {
        Map<String, Object> properties =
                Map.of("javax.persistence.fetchgraph", entityManager.getEntityGraph(GRAPH_NAME));
        return Optional.ofNullable(entityManager.find(Comment.class, id, properties));
    }

    @Override
    public List<Comment> getByContent(String content) {
        TypedQuery<Comment> query = entityManager.createQuery(
                "select c from Comment c where c.content = :content",
                Comment.class);
        query.setParameter("content", content);
        query.setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph(GRAPH_NAME));
        return query.getResultList();
    }

    @Override
    public List<Comment> getAll() {
        TypedQuery<Comment> query = entityManager.createQuery(
                "select c from Comment c ",
                Comment.class);
        query.setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph(GRAPH_NAME));
        return query.getResultList();
    }

    @Override
    public void deleteComment(long id) {
        Query query = entityManager.createQuery(
                "delete from Comment c where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public long count() {
        TypedQuery<Long> query = entityManager.createQuery(
                "select count(c) from Comment c",
                Long.class);
        Long count = query.getSingleResult();
        return count == null ? 0 : count;
    }
}
