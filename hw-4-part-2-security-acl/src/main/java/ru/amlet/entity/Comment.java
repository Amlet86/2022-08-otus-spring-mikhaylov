package ru.amlet.entity;

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
@NamedEntityGraph(name = "comment-entity-graph",
        attributeNodes = {@NamedAttributeNode(value = "book", subgraph = "book-subgraph")},
        subgraphs = {@NamedSubgraph(name = "book-subgraph",
                attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("genre")})})
public class Comment implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    public long getBookId() {
        return Objects.nonNull(book) ? book.getId() : 0;
    }

    public String getBookName() {
        return Objects.nonNull(book) ? book.getName() : "";
    }
}
