package ru.amlet.entity.jpa;

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
public class BookJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE)
    private AuthorJpa author;

    @ManyToOne(cascade = CascadeType.MERGE)
    private GenreJpa genre;

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
