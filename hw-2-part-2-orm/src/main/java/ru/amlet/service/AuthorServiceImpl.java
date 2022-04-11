package ru.amlet.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.amlet.entity.Book;
import ru.amlet.repositories.AuthorRepository;
import ru.amlet.entity.Author;

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
        return authorRepository.saveAuthor(new Author(name));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> findById(long id) {
        return authorRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findByName(String name) {
        return authorRepository.getByName(name);
    }

    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return authorRepository.getAll();
    }

    @Override
    @Transactional
    public void updateAuthor(long id, String name) {
        authorRepository.saveAuthor(new Author(id, name));
    }

    @Override
    @Transactional
    public void deleteAuthor(long id) {
        authorRepository.deleteAuthor(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return authorRepository.count();
    }

}
