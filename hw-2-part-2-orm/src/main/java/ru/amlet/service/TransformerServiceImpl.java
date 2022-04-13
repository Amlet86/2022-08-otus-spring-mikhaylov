package ru.amlet.service;

import org.springframework.stereotype.Service;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Comment;
import ru.amlet.entity.Genre;

import java.util.List;

@Service
public class TransformerServiceImpl implements TransformerService {

    @Override
    public String bookTransform(Book book) {
        return "Name: " +
                book.getName() +
                ", Author: " +
                book.getAuthorName() +
                ", Genre: " +
                book.getGenreName() +
                ";";
    }

    @Override
    public String booksTransform(List<Book> books) {
        if (books.isEmpty()) {
            return "List of books is empty.";
        }
        StringBuilder stringBuilder = new StringBuilder();
        books.forEach(book -> {
            stringBuilder.append(bookTransform(book));
            stringBuilder.append(System.lineSeparator());
        });
        return stringBuilder.toString();
    }

    @Override
    public String authorTransform(Author author) {
        return "Name: " + author.getName() + ";";
    }

    @Override
    public String authorsTransform(List<Author> authors) {
        if (authors.isEmpty()) {
            return "List of authors is empty.";
        }
        StringBuilder stringBuilder = new StringBuilder();
        authors.forEach(author -> {
            stringBuilder.append(authorTransform(author));
            stringBuilder.append(System.lineSeparator());
        });
        return stringBuilder.toString();
    }

    @Override
    public String genreTransform(Genre genre) {
        return "Name: " + genre.getName() + ";";
    }

    @Override
    public String genresTransform(List<Genre> genres) {
        if (genres.isEmpty()) {
            return "List of genres is empty.";
        }
        StringBuilder stringBuilder = new StringBuilder();
        genres.forEach(genre -> {
            stringBuilder.append(genreTransform(genre));
            stringBuilder.append(System.lineSeparator());
        });
        return stringBuilder.toString();
    }

    @Override
    public String commentTransform(Comment comment) {
        return "Content: " +
                comment.getContent() +
                ", of book: " +
                comment.getBookName() +
                ";";
    }

    @Override
    public String commentsTransform(List<Comment> comments) {
        if (comments.isEmpty()) {
            return "List of comments is empty.";
        }
        StringBuilder stringBuilder = new StringBuilder();
        comments.forEach(comment -> {
            stringBuilder.append(commentTransform(comment));
            stringBuilder.append(System.lineSeparator());
        });
        return stringBuilder.toString();
    }
}
