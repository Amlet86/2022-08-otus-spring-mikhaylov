package ru.amlet.service;

import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Comment;
import ru.amlet.entity.Genre;

import java.util.List;

public interface TransformerService {

    String bookTransform(Book book);

    String booksTransform(List<Book> books);

    String authorTransform(Author author);

    String authorsTransform(List<Author> authors);

    String genreTransform(Genre genre);

    String genresTransform(List<Genre> genres);

    String commentTransform(Comment comment);

    String commentsTransform(List<Comment> comments);
}
