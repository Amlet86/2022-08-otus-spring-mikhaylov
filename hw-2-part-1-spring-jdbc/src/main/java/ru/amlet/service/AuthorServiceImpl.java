package ru.amlet.service;

import org.springframework.stereotype.Service;
import ru.amlet.dao.AuthorDao;
import ru.amlet.entity.Author;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public long createAuthor(Author author) {
        return authorDao.createAuthor(author);
    }

    @Override
    public Author findById(long id) {
        return authorDao.getById(id);
    }

    @Override
    public Author findByName(String name) {
        return authorDao.getByName(name);
    }

    @Override
    public void updateAuthor(Author author) {
        authorDao.updateAuthor(author);
    }

    @Override
    public void deleteAuthor(long id) {
        authorDao.deleteAuthor(id);
    }

}
