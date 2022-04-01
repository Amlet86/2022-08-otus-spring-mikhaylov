package ru.amlet.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Genre;
import ru.amlet.exception.BookException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long createBook(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", book.getName());
        params.addValue("authorId", book.getAuthorId());
        params.addValue("genreId", book.getGenreId());

        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into books (name, author_id, genre_id) values (:name, :authorId, :genreId)",
                params, kh);

        return kh.getKey().longValue();
    }

    @Override
    public Book getByName(String name) {
        try {
            return jdbc.queryForObject("select id, name, author_id, genre_id from books where name = :name",
                    Map.of("name", name), new BookMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new BookException(String.format("Book name: %s does not exist", name));
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new BookException(String.format("More than one book with the same name %s", name));
        }
    }

    @Override
    public Book getById(long id) {
        try {
            return jdbc.queryForObject("select id, name, author_id, genre_id from books where id = :id",
                    Map.of("id", id), new BookMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new BookException(String.format("Book id: %s does not exist", id));
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new BookException(String.format("More than one book with the same id %s", id));
        }
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select id, name, author_id, genre_id from books", new BookMapper());
    }

    @Override
    public void updateBook(Book book) {
        jdbc.update("update books set name = :name, author_id = :authorId, genre_id = :genreId where id = :id",
                Map.of("id", book.getId(),
                        "name", book.getName(),
                        "authorId", book.getAuthor().getId(),
                        "genreId", book.getGenre().getId()));
    }

    @Override
    public void deleteBook(long id) {
        jdbc.update("delete from books where id = :id",
                Map.of("id", id));
    }

    @Override
    public int count() {
        Integer count = jdbc.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);
        return count == null ? 0 : count;
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Author author = Author.builder().id(resultSet.getLong("author_id")).build();
            Genre genre = Genre.builder().id(resultSet.getLong("genre_id")).build();
            return Book.builder()
                    .id(id)
                    .name(name)
                    .author(author)
                    .genre(genre)
                    .build();
        }
    }

}
