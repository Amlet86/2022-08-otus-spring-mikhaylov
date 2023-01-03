package ru.amlet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.amlet.dto.AuthorDto;
import ru.amlet.entity.Author;
import ru.amlet.repositories.AuthorRepository;

@RestController
@RequiredArgsConstructor
public class AuthorRestController {

    private final AuthorRepository authorRepository;

    @GetMapping("/api/authors")
    public Flux<AuthorDto> findByName(@RequestParam("name") String name) {
        if (ObjectUtils.isEmpty(name)) {
            return authorRepository.findAll()
                    .map(AuthorDto::toDto);
        } else {
            return authorRepository.findByName(name)
                    .map(AuthorDto::toDto);
        }
    }

    @PutMapping("/api/authors")
    public Mono<AuthorDto> editAuthor(@RequestBody AuthorDto authorDto) {
        return authorRepository
                .save(new Author(authorDto.getId(), authorDto.getName()))
                .map(AuthorDto::toDto);
    }

    @PostMapping("/api/authors")
    public Mono<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        return authorRepository
                .save(new Author(authorDto.getId(), authorDto.getName()))
                .map(AuthorDto::toDto);
    }

}
