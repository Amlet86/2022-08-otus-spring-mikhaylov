package ru.amlet.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;
import ru.amlet.entity.Book;
import ru.amlet.service.BookService;
import ru.amlet.service.TransformerService;

import java.util.List;

@Component
@ShellComponent
public class BookController {

    private final BookService bookService;
    private final TransformerService transformerService;

    public BookController(BookService bookService, TransformerService transformerService) {
        this.bookService = bookService;
        this.transformerService = transformerService;
    }

    @ShellMethod(value = "Create new book", key = {"cnb", "createNewBook"})
    public long createBook(String name, String authorName, String genreName) {
        return bookService.createBook(name, authorName, genreName);
    }

    @ShellMethod(value = "Find book", key = {"fbi", "findBookById"})
    public String findById(long id) {
        Book book = bookService.findById(id);
        return transformerService.bookTransform(book);
    }

    @ShellMethod(value = "Find book", key = {"fbn", "findBookByName"})
    public String findByName(String name) {
        Book book = bookService.findByName(name);
        return transformerService.bookTransform(book);
    }

    @ShellMethod(value = "Find all books", key = {"fab", "findAllBooks"})
    public String findAll() {
        List<Book> books = bookService.findAll();
        return transformerService.booksTransform(books);
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
