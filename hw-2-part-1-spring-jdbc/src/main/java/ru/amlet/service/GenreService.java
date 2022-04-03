package ru.amlet.service;

import ru.amlet.entity.Genre;

public interface GenreService {

    long createGenre(Genre genre);

    Genre findById(long id);

    Genre findByName(String name);

    void updateGenre(Genre genre);

    void deleteGenre(long id);

}
