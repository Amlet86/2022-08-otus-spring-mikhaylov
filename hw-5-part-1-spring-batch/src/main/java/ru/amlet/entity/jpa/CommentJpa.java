package ru.amlet.entity.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class CommentJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private BookJpa bookJpa;

    public long getBookId() {
        return Objects.nonNull(bookJpa) ? bookJpa.getId() : 0;
    }

    public String getBookName() {
        return Objects.nonNull(bookJpa) ? bookJpa.getName() : "";
    }
}
