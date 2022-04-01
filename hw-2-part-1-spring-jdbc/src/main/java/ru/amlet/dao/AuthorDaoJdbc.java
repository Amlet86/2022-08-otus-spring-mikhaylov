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
import ru.amlet.exception.AuthorException;
import ru.amlet.exception.BookException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long createAuthor(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", author.getName());

        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into authors values (':name')",
                params, kh);

        return kh.getKey().longValue();
    }

    @Override
    public Author getByName(String name) {
        try {
            return jdbc.queryForObject("select id, name from authors where name = :name",
                    Map.of("name", name), new AuthorMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new AuthorException(String.format("Author name: %s does not exist", name));
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new AuthorException(String.format("More than one author with the same name %s", name));
        }
    }

    @Override
    public Author getById(long id) {
        try {
            return jdbc.queryForObject("select id, name from authors where id = :id",
                    Map.of("id", id), new AuthorMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new AuthorException(String.format("Author id: %s does not exist", id));
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new AuthorException(String.format("More than one author with the same id %s", id));
        }
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select id, name from authors", new AuthorMapper());
    }

    @Override
    public void updateAuthor(Author author) {
        jdbc.update("update authors set name = :name where id = :id",
                Map.of("id", author.getId(),
                        "name", author.getName()));
    }

    @Override
    public void deleteAuthor(long id) {
        jdbc.update("delete from authors where id = :id",
                Map.of("id", id));
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return Author.builder()
                    .id(id)
                    .name(name)
                    .build();
        }
    }
}
