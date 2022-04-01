package ru.amlet.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.amlet.dao.BookDao;
import ru.amlet.dao.GenreDao;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Genre;
import ru.amlet.exception.AuthorException;
import ru.amlet.exception.BookException;
import ru.amlet.exception.GenreException;

import java.util.Objects;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookServiceImpl(BookDao bookDao, AuthorService authorService, GenreService genreService) {
        this.bookDao = bookDao;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Override
    public long createBook(Book book) {
        book.setAuthor(authorService.find(book.getAuthor()));
        book.setGenre(genreService.find(book.getGenre()));
        return bookDao.createBook(book);
    }

    @Override
    public Book find(@NonNull Book book) {
        Book resultBook;
        if (book.getId() != 0) {
            resultBook = bookDao.getById(book.getId());
        } else if (book.getName() != null) {
            resultBook = bookDao.getByName(book.getName());
        } else {
            throw new BookException("Book with id: 0, name: null can not exist");
        }
        resultBook.setAuthor(authorService.find(resultBook.getAuthor()));
        resultBook.setGenre(genreService.find(resultBook.getGenre()));
        return resultBook;
    }

    @Override
    public void updateBook(Book book) {
        book.setAuthor(authorService.find(book.getAuthor()));
        book.setGenre(genreService.find(book.getGenre()));
        bookDao.updateBook(book);
    }

    @Override
    public void deleteBook(long id) {
        bookDao.deleteBook(id);
    }

    @Override
    public int count() {
        return bookDao.count();
    }

}
