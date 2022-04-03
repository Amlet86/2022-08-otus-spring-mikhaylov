package ru.amlet.service;

import org.springframework.stereotype.Service;
import ru.amlet.dao.GenreDao;
import ru.amlet.entity.Genre;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public long createGenre(Genre genre) {
        return genreDao.createGenre(genre);
    }

    @Override
    public Genre findById(long id) {
        return genreDao.getById(id);
    }

    @Override
    public Genre findByName(String name) {
        return genreDao.getByName(name);
    }

    @Override
    public void updateGenre(Genre genre) {
        genreDao.updateGenre(genre);
    }

    @Override
    public void deleteGenre(long id) {
        genreDao.deleteGenre(id);
    }

}
