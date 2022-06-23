package ru.amlet.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.amlet.dto.BookDto;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Genre;
import ru.amlet.exception.BookException;
import ru.amlet.service.AuthorService;
import ru.amlet.service.BookService;
import ru.amlet.service.GenreService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping("/books")
    public String findAll(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books/books";
    }

    @GetMapping("/books/create")
    public String createPage(Model model) {
        List<Author> authors = authorService.findAll();
        List<Genre> genres = genreService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "books/createBook";
    }

    @Validated
    @PostMapping("/books/create")
    public String createBook(@Valid @ModelAttribute("book") BookDto bookDto,
                             BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "books/createBook";
        }
        Book book = bookDto.toDomainObject();
        bookService.createBook(book.getName(), book.getAuthorName(), book.getGenreName(), authentication);
        return "redirect:/books";
    }

    @GetMapping("books/find")
    public String findByName(@RequestParam(value = "name", required = false) String name, Model model) {
        List<Book> books = bookService.findByName(name);
        model.addAttribute("books", books);
        return "books/findBook";
    }

    @GetMapping("/books/edit")
    public String editPage(@RequestParam("id") int id, Model model) {
        Book book = bookService.findById(id).orElseThrow(BookException::new);
        List<Author> authors = authorService.findAll();
        List<Genre> genres = genreService.findAll();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "books/editBook";
    }

    @Validated
    @PostMapping("/books/edit")
    public String saveBook(@Valid @ModelAttribute("book") BookDto bookDto,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/editBook";
        }
        Book book = bookDto.toDomainObject();
        bookService.updateBook(book.getId(), book.getName(), book.getAuthorName(), book.getGenreName());
        return "redirect:/books";
    }

    @GetMapping("/books/delete")
    public String deletePage(@RequestParam("id") int id, Model model) {
        Book book = bookService.findById(id).orElseThrow(BookException::new);
        model.addAttribute("book", book);
        return "books/deleteBook";
    }

    @PostMapping("/books/delete")
    public String deleteBook(@RequestParam("id") long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

}
