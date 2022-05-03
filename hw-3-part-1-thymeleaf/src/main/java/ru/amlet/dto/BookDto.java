package ru.amlet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Genre;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private long id;

    @NotBlank(message = "{name-field-shouldnt-be-blank}")
    @Size(min = 2, max = 100, message = "{name-field-should-has-expected-size}")
    private String name;

    private Author author;

    private Genre genre;

    public Book toDomainObject(){
        return new Book(id, name, author, genre);
    }

    public static BookDto fromDomainObject(Book book) {
        return new BookDto(book.getId(), book.getName(), book.getAuthor(), book.getGenre());
    }
}
