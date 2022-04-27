package ru.amlet.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    private String name;

    @DBRef
    private Author author;

    @DBRef
    private Genre genre;

    public String getAuthorName() {
        return Objects.nonNull(author) ? author.getName() : "";
    }

    public String getAuthorId() {
        return Objects.nonNull(author) ? author.getId() : null;
    }

    public String getGenreName() {
        return Objects.nonNull(genre) ? genre.getName() : "";
    }

    public String getGenreId() {
        return Objects.nonNull(genre) ? genre.getId() : null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Book otherBook = (Book) obj;
        if (!Objects.equals(this.id, otherBook.id)) {
            return false;
        }
        if (!Objects.equals(this.name, otherBook.name)) {
            return false;
        }
        if (!Objects.equals(this.getAuthorName(), otherBook.getAuthorName())) {
            return false;
        }
        if (!Objects.equals(this.getGenreName(), otherBook.getGenreName())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (this.getAuthorName() != null ? this.getAuthorName().hashCode() : 0);
        hash = 53 + hash + (this.getGenreName() != null ? this.getGenreName().hashCode() : 0);
        return hash;
    }
}
