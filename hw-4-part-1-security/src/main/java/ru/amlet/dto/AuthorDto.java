package ru.amlet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.amlet.entity.Author;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private long id;

    @NotBlank(message = "{name-field-shouldnt-be-blank}")
    @Size(min = 2, max = 100, message = "{name-field-should-has-expected-size}")
    private String name;

    public AuthorDto(String name) {
        this.name = name;
    }

    public Author toDomainObject(){
        return new Author(id, name);
    }

    public static AuthorDto fromDomainObject(Author author) {
        return new AuthorDto(author.getId(), author.getName());
    }
}
