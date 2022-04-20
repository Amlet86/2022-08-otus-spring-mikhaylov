package ru.amlet.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
@NamedEntityGraph(name = "book-entity-graph", attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("genre")})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Author author;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Book otherBook = (Book) obj;
        if (this.id != otherBook.id) {
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
