package ru.amlet.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;
import ru.amlet.entity.Book;
import ru.amlet.service.BookService;

import java.util.List;

@Component
@ShellComponent
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(value = "Create new book", key = {"cnb", "createNewBook"})
    public long createBook(String name, String authorName, String genreName) {
        return bookService.createBook(name, authorName, genreName);
    }

    @ShellMethod(value = "Find book", key = {"fbi", "findBookById"})
    public Book findById(long id) {
        return bookService.findById(id);
    }

    @ShellMethod(value = "Find book", key = {"fbn", "findBookByName"})
    public Book findByName(String name) {
        return bookService.findByName(name);
    }

    @ShellMethod(value = "Find all books", key = {"fab", "findAllBooks"})
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @ShellMethod(value = "Update book", key = {"ub", "updateBook"})
    public void updateBook(long id, String name, String authorName, String genreName) {
        bookService.updateBook(id, name, authorName, genreName);
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
