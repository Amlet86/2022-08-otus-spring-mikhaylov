package ru.amlet.controller;

import org.springframework.web.bind.annotation.*;
import ru.amlet.dto.BookDto;
import ru.amlet.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<BookDto> findAll() {
        return bookService.findAll().stream()
                .map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/books/{name}")
    public List<BookDto> findByName(@PathVariable("name") String name) {
        return bookService.findByName(name).stream()
                .map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable("id") long id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/books")
    public void editPage(@RequestParam("id") int id,
                         @RequestParam("name") String name,
                         @RequestParam("authorName") String authorName,
                         @RequestParam("genreName") String genreName) {
        bookService.updateBook(id, name, authorName, genreName);
    }

    @PostMapping("/books")
    public BookDto createBook(@RequestParam("name") String name,
                             @RequestParam("authorName") String authorName,
                             @RequestParam("genreName") String genreName) {
        return BookDto.toDto(bookService.createBook(name, authorName, genreName));
    }

}
