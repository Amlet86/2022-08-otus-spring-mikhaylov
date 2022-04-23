package ru.amlet.service;

import org.springframework.stereotype.Service;
import ru.amlet.entity.Genre;
import ru.amlet.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre createGenre(String name) {
        return genreRepository.save(new Genre(name));
    }

    @Override
    public Optional<Genre> findById(String id) {
        return genreRepository.findById(id);
    }

    @Override
    public List<Genre> findByName(String name) {
        return genreRepository.findByName(name);
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public void updateGenre(String id, String name) {
        genreRepository.save(new Genre(id, name));
    }

    @Override
    public void deleteGenre(String id) {
        genreRepository.deleteAllById(List.of(id));
    }

    @Override
    public long count() {
        return genreRepository.count();
    }

}
