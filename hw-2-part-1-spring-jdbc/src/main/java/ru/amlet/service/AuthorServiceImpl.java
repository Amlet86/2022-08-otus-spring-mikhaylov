package ru.amlet.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.amlet.dao.AuthorDao;
import ru.amlet.dao.BookDao;
import ru.amlet.dao.GenreDao;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.exception.AuthorException;

import java.util.List;

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
    public Author find(@NonNull Author author) {
        if (author.getId() != 0) {
            return authorDao.getById(author.getId());
        }
        if (author.getName() != null) {
            return authorDao.getByName(author.getName());
        }
        throw new AuthorException("Author with id: 0, name: null can not exist");
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
