package ru.amlet.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    private String content;

    @DBRef
    private Book book;

    public String getBookId() {
        return Objects.nonNull(book) ? book.getId() : null;
    }

    public String getBookName() {
        return Objects.nonNull(book) ? book.getName() : "";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Comment otherComment = (Comment) obj;
        if (!Objects.equals(this.id, otherComment.id)) {
            return false;
        }
        if (!Objects.equals(this.content, otherComment.content)) {
            return false;
        }
        if (!Objects.equals(this.getBookName(), otherComment.getBookName())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 53 * hash + (this.content != null ? this.content.hashCode() : 0);
        return hash;
    }

}
