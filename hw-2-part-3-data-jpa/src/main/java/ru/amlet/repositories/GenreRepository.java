package ru.amlet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.amlet.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findByName(String name);

}
