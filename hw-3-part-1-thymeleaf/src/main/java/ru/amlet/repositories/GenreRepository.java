package ru.amlet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.amlet.entity.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findByName(String name);

}
