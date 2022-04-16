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
}
