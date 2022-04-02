package ru.amlet.entity;

import lombok.*;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class Book {

    private final long id;

    private final String name;

    private Author author;

    private Genre genre;

    public String getAuthorName() {
        return Objects.nonNull(author) ? author.getName() : "";
    }

    public long getAuthorId() {
        return Objects.nonNull(author) ? author.getId() : 0;
    }

    public String getGenreName() {
        return Objects.nonNull(genre) ? genre.getName() : "";
    }

    public long getGenreId() {
        return Objects.nonNull(genre) ? genre.getId() : 0;
    }
}
