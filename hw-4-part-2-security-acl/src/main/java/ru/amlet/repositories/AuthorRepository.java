package ru.amlet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.amlet.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @PostFilter("hasPermission(filterObject, 'READ') or hasPermission(filterObject, 'ADMINISTRATION')")
    List<Author> findByName(String name);

    @Override
    @PostFilter("hasPermission(filterObject, 'READ') or hasPermission(filterObject, 'ADMINISTRATION')")
    List<Author> findAll();

}
