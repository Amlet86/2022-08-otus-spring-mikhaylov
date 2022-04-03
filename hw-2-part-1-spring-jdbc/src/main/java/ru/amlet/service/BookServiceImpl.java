package ru.amlet.service;

import org.springframework.stereotype.Service;
import ru.amlet.dao.BookDao;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Genre;

import java.util.List;

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
    public long createBook(String name, String authorName, String genreName) {
        Author author = authorService.findByName(authorName);
        Genre genre = genreService.findByName(genreName);
        Book book = Book.builder()
                .name(name)
                .author(author)
                .genre(genre)
                .build();
        return bookDao.createBook(book);
    }

    @Override
    public Book findById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public Book findByName(String name) {
        return bookDao.getByName(name);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.getAll();
    }

    @Override
    public void updateBook(long id, String name, String authorName, String genreName) {
        Author author = authorService.findByName(name);
        Genre genre = genreService.findByName(genreName);
        Book book = Book.builder()
                .id(id)
                .name(name)
                .author(author)
                .genre(genre)
                .build();
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
