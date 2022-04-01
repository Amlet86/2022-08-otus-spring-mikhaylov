package ru.amlet.dao;

import ru.amlet.entity.Genre;

import java.util.List;

public interface GenreDao {

    long createGenre(Genre genre);

    Genre getByName(String name);

    Genre getById(long id);

    List<Genre> getAll();

    void updateGenre(Genre genre);

    void deleteGenre(long id);

}
