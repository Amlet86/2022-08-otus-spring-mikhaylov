package ru.amlet.service;

import org.springframework.stereotype.Service;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Comment;
import ru.amlet.entity.Genre;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
            return "List of book is empty.";
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
        throw new UnsupportedOperationException();
    }

    @Override
    public String authorsTransform(List<Author> authors) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String genreTransform(Genre genre) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String genresTransform(List<Genre> genres) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String commentTransform(Comment comment) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String commentsTransform(List<Comment> comments) {
        throw new UnsupportedOperationException();
    }
}
