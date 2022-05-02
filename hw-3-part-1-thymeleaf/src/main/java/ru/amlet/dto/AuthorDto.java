package ru.amlet.dto;

import ru.amlet.entity.Author;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorDto {

    private long id;

    @NotBlank(message = "{name-field-shouldnt-be-blank}")
    @Size(min = 2, max = 100, message = "{name-field-should-has-expected-size}")
    private String name;

    public AuthorDto() {
    }

    public AuthorDto(String name) {
        this.name = name;
    }

    public AuthorDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author toDomainObject(){
        return new Author(id, name);
    }

    public static AuthorDto fromDomainObject(Author author) {
        return new AuthorDto(author.getId(), author.getName());
    }
}
