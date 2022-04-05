package ru.amlet.service;

import org.springframework.stereotype.Service;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
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
}
