package ru.amlet.service;

import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Genre;
import ru.amlet.exception.AuthorException;
import ru.amlet.exception.AuthorityException;
import ru.amlet.exception.GenreException;
import ru.amlet.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final PermissionService permissionService;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorService authorService,
                           GenreService genreService,
                           PermissionService permissionService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.genreService = genreService;
        this.permissionService = permissionService;
    }

    @Override
    @Transactional
    public Book createBook(String name, String authorName, String genreName, Authentication authentication) {
        List<Author> authors = authorService.findByName(authorName);
        Author author = checkAuthorsList(authors, authorName);
        List<Genre> genres = genreService.findByName(genreName);
        Genre genre = checkGenresList(genres, genreName);
        Book book = bookRepository.save(Book.builder()
                .name(name)
                .author(author)
                .genre(genre)
                .build());
        String authority = getAuthority(authentication);
        permissionService.addPermissionForAuthority(book, BasePermission.ADMINISTRATION, authority);
        return book;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void updateBook(long id, String name, String authorName, String genreName) {
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
    @Transactional
    public void deleteBook(long id) {
        bookRepository.deleteAllById(List.of(id));
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

    private String getAuthority(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElseThrow(() -> new AuthorityException("Didn't find any authority"));
    }

}
