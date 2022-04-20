package ru.amlet.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public Author createAuthor(String name) {
        return authorRepository.save(new Author(name));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> findById(long id) {
        return authorRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public void updateAuthor(long id, String name) {
        authorRepository.save(new Author(id, name));
    }

    @Override
    @Transactional
    public void deleteAuthor(long id) {
        authorRepository.deleteAllById(List.of(id));
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return authorRepository.count();
    }

}
