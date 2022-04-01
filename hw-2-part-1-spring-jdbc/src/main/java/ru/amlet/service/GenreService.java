package ru.amlet.service;

import ru.amlet.entity.Genre;

public interface GenreService {

    long createGenre(Genre genre);

    Genre find(Genre genre);

    void updateGenre(Genre genre);

    void deleteGenre(long id);

}
