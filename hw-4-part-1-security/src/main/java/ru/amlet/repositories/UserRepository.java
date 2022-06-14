package ru.amlet.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.amlet.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    String GRAPH_NAME = "user-entity-graph";

    @EntityGraph(GRAPH_NAME)
    Optional<User> findByName(String name);

}
