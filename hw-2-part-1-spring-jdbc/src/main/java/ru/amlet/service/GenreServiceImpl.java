package ru.amlet.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.amlet.dao.AuthorDao;
import ru.amlet.dao.GenreDao;
import ru.amlet.entity.Author;
import ru.amlet.entity.Genre;
import ru.amlet.exception.AuthorException;

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
    public Genre find(@NonNull Genre genre) {
        if (genre.getId() != 0) {
            return genreDao.getById(genre.getId());
        }
        if (genre.getName() != null) {
            return genreDao.getByName(genre.getName());
        }
        throw new AuthorException("Author with id: 0, name: null can not exist");
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
