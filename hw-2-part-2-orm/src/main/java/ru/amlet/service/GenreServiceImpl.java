package ru.amlet.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.amlet.entity.Author;
import ru.amlet.repositories.GenreRepository;
import ru.amlet.entity.Genre;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional
    public Genre createGenre(String name) {
        return genreRepository.saveGenre(new Genre(name));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> findById(long id) {
        return genreRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findByName(String name) {
        return genreRepository.getByName(name);
    }

    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return genreRepository.getAll();
    }

    @Override
    @Transactional
    public void updateGenre(long id, String name) {
        genreRepository.saveGenre(new Genre(id, name));
    }

    @Override
    @Transactional
    public void deleteGenre(long id) {
        genreRepository.deleteGenre(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return genreRepository.count();
    }

}
