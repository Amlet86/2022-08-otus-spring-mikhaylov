package ru.amlet.service;

import org.springframework.stereotype.Service;
import ru.amlet.entity.Author;
import ru.amlet.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author create(String name) {
        return authorRepository.save(new Author(name));
    }

    @Override
    public Optional<Author> findById(String id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public void update(String id, String name) {
        authorRepository.save(new Author(id, name));
    }

    @Override
    public void deleteById(String id) {
        authorRepository.deleteById(id);
    }

    @Override
    public long count() {
        return authorRepository.count();
    }

}
