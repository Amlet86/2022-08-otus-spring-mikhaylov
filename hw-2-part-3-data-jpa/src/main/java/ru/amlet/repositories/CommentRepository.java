package ru.amlet.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.amlet.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    String GRAPH_NAME = "comment-entity-graph";

    @EntityGraph(GRAPH_NAME)
    Optional<Comment> findById(long id);

    @EntityGraph(GRAPH_NAME)
    List<Comment> findByContent(String content);

    @EntityGraph(GRAPH_NAME)
    List<Comment> findAll();


}
