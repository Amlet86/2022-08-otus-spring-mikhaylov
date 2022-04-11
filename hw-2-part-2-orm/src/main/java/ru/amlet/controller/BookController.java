package ru.amlet.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;
import ru.amlet.entity.Book;
import ru.amlet.service.BookService;
import ru.amlet.service.TransformerService;

import java.util.List;
import java.util.Optional;

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
    public String createBook(String name, String authorName, String genreName) {
        Book book = bookService.createBook(name, authorName, genreName);
        return transformerService.bookTransform(book);
    }

    @ShellMethod(value = "Find book by id", key = {"fbi", "findBookById"})
    public String findById(long id) {
        Optional<Book> book = bookService.findById(id);
        return book.map(transformerService::bookTransform)
                .orElse(String.format("Book id: %s not found.", id));
    }

    @ShellMethod(value = "Find book by name", key = {"fbn", "findBookByName"})
    public String findByName(String name) {
        List<Book> books = bookService.findByName(name);
        return transformerService.booksTransform(books);
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
    public long count() {
        return bookService.count();
    }

}
