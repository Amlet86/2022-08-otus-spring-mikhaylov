package ru.amlet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.amlet.dto.BookDto;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Genre;
import ru.amlet.repositories.BookRepository;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final BookRepository bookRepository;

    @GetMapping("/api/books")
    public Flux<BookDto> findByName(@RequestParam("name") String name) {
        if (ObjectUtils.isEmpty(name)) {
            return bookRepository.findAll()
                    .map(BookDto::toDto);
        } else {
            return bookRepository.findByName(name)
                    .map(BookDto::toDto);
        }
    }

    @DeleteMapping("/api/books/{id}")
    public Mono<Void> deleteBook(@PathVariable("id") String id) {
        return bookRepository.deleteById(id);
    }

    @PutMapping("/api/books")
    public Mono<BookDto> editBook(@RequestBody BookDto bookDto) {
        return bookRepository.findById(bookDto.getId())
                .map(book -> new Book(
                        bookDto.getId(),
                        bookDto.getName(),
                        book.getAuthor(),
                        book.getGenre()))
                .flatMap(bookRepository::save)
                .map(BookDto::toDto);
    }

    @PostMapping("/api/books")
    public Mono<BookDto> createBook(@RequestBody BookDto bookDto) {

        return bookRepository.save(
                        Book.builder()
                                .name(bookDto.getName())
                                .author(new Author(bookDto.getAuthor().getId(), bookDto.getAuthor().getName()))
                                .genre(new Genre(bookDto.getGenre().getId(), bookDto.getGenre().getName()))
                                .build())
                .map(BookDto::toDto);
    }

}
