package ru.amlet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private long id;

    private String name;

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
