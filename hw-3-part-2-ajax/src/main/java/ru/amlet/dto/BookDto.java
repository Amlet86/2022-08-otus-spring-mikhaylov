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

    private long id;

    private String name;

    private String authorName;

    private String genreName;

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getName(), book.getAuthorName(), book.getGenreName());
    }
}
