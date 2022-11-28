package ru.amlet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.amlet.entity.Author;
import ru.amlet.entity.Genre;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {

    private String id;

    private String name;

    public static GenreDto toDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }
}
