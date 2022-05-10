package ru.amlet.controller;

import org.springframework.util.ObjectUtils;
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

    @GetMapping("/api/books")
    public List<BookDto> findByName(@RequestParam("name") String name) {
        if (ObjectUtils.isEmpty(name)) {
            return bookService.findAll().stream()
                    .map(BookDto::toDto)
                    .collect(Collectors.toList());
        } else {
            return bookService.findByName(name).stream()
                    .map(BookDto::toDto)
                    .collect(Collectors.toList());
        }
    }

    @DeleteMapping("/api/books/{id}")
    public void deleteBook(@PathVariable("id") long id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/api/books")
    public void editBook(@RequestBody BookDto bookDto) {
        bookService.updateBook(bookDto.getId(), bookDto.getName(), bookDto.getAuthorName(), bookDto.getGenreName());
    }

    @PostMapping("/api/books")
    public BookDto createBook(@RequestBody BookDto bookDto) {
        return BookDto.toDto(bookService.createBook(bookDto.getName(), bookDto.getAuthorName(), bookDto.getGenreName()));
    }

}
