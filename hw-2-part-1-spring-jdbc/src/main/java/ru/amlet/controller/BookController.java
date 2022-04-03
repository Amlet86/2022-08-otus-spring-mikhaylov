package ru.amlet.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Genre;
import ru.amlet.service.BookService;

@Component
@ShellComponent
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(value = "Create new book", key = {"cnb", "createNewBook"})
    public long createBook(String name, String authorName, String genreName) {
        Author author = new Author();
        author.setName(authorName);
        Genre genre = new Genre();
        genre.setName(genreName);
        Book book = Book.builder()
                .name(name)
                .author(author)
                .genre(genre)
                .build();
        return bookService.createBook(book);
    }

    @ShellMethod(value = "Find book", key = {"fb", "findBook"})
    public Book find(String name) {
        Book book = Book.builder()
                .name(name)
                .build();
        return bookService.find(book);
    }

    @ShellMethod(value = "Update book", key = {"ub", "updateBook"})
    public void updateBook(long id, String name, String authorName, String genreName) {
        Author author = new Author();
        author.setName(authorName);
        Genre genre = new Genre();
        genre.setName(genreName);
        Book book = Book.builder()
                .id(id)
                .name(name)
                .author(author)
                .genre(genre)
                .build();
        bookService.updateBook(book);
    }

    @ShellMethod(value = "Delete book", key = {"db", "deleteBook"})
    public void deleteBook(long id) {
        bookService.deleteBook(id);
    }

    @ShellMethod(value = "Count books", key = {"cbs", "countBooks"})
    public int count() {
        return bookService.count();
    }

}
