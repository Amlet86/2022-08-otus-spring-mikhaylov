package ru.amlet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.amlet.entity.Book;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String id;

    private String name;

    private AuthorDto author;

    private GenreDto genre;

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getName(), AuthorDto.toDto(book.getAuthor()), GenreDto.toDto(book.getGenre()));
    }
}
