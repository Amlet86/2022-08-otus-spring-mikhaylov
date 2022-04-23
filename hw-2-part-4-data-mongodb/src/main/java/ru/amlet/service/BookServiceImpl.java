package ru.amlet.service;

import org.springframework.stereotype.Service;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Genre;
import ru.amlet.exception.AuthorException;
import ru.amlet.exception.GenreException;
import ru.amlet.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, GenreService genreService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Override
    public Book createBook(String name, String authorName, String genreName) {
        List<Author> authors = authorService.findByName(authorName);
        Author author = checkAuthorsList(authors, authorName);
        List<Genre> genres = genreService.findByName(genreName);
        Genre genre = checkGenresList(genres, genreName);
        Book book = Book.builder()
                .name(name)
                .author(author)
                .genre(genre)
                .build();
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void updateBook(String id, String name, String authorName, String genreName) {
        List<Author> authors = authorService.findByName(authorName);
        Author author = checkAuthorsList(authors, authorName);
        List<Genre> genres = genreService.findByName(genreName);
        Genre genre = checkGenresList(genres, genreName);
        Book book = Book.builder()
                .id(id)
                .name(name)
                .author(author)
                .genre(genre)
                .build();
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(String id) {
        bookRepository.deleteAllById(List.of(id));
    }

    @Override
    public long count() {
        return bookRepository.count();
    }

    private Author checkAuthorsList(List<Author> authors, String authorName) {
        if (authors.size() == 0) {
            throw new AuthorException(String.format("With name: %s no one author was found", authorName));
        } else if (authors.size() > 1) {
            throw new AuthorException(String.format("With name: %s more than one author found", authorName));
        } else {
            return authors.get(0);
        }
    }

    private Genre checkGenresList(List<Genre> genres, String genreName) {
        if (genres.size() == 0) {
            throw new GenreException(String.format("With name: %s no one genre was found", genreName));
        } else if (genres.size() > 1) {
            throw new GenreException(String.format("With name: %s more than one genre found", genreName));
        } else {
            return genres.get(0);
        }
    }

}
