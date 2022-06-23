package ru.amlet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.amlet.entity.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByName(String name);

}
