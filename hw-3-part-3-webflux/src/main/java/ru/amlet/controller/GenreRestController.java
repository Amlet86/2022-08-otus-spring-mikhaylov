package ru.amlet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.amlet.dto.AuthorDto;
import ru.amlet.dto.GenreDto;
import ru.amlet.entity.Author;
import ru.amlet.entity.Genre;
import ru.amlet.repositories.GenreRepository;

@RestController
@RequiredArgsConstructor
public class GenreRestController {

    private final GenreRepository genreRepository;

    @GetMapping("/api/genres")
    public Flux<GenreDto> findByName(@RequestParam("name") String name) {
        if (ObjectUtils.isEmpty(name)) {
            return genreRepository.findAll()
                    .map(GenreDto::toDto);
        } else {
            return genreRepository.findByName(name)
                    .map(GenreDto::toDto);
        }
    }

    @PutMapping("/api/genres")
    public Mono<GenreDto> editGenre(@RequestBody GenreDto genreDto) {
        return genreRepository
                .save(new Genre(genreDto.getId(), genreDto.getName()))
                .map(GenreDto::toDto);
    }

    @PostMapping("/api/genres")
    public Mono<ResponseEntity<GenreDto>> createGenre(@RequestBody GenreDto genreDto) {
        return genreRepository
                .save(new Genre(genreDto.getId(), genreDto.getName()))
                .map(GenreDto::toDto)
                .map(ResponseEntity::ok);
    }

}
