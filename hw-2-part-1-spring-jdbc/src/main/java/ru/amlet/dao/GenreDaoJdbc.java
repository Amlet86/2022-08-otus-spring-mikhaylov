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
import ru.amlet.entity.Genre;
import ru.amlet.exception.AuthorException;
import ru.amlet.exception.GenreException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long createGenre(Genre genre) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", genre.getName());

        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into genres values (':name')",
                params, kh);

        return kh.getKey().longValue();
    }

    @Override
    public Genre getByName(String name) {
        try {
            return jdbc.queryForObject("select id, name from genres where name = :name",
                    Map.of("name", name), new GenreMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new GenreException(String.format("Genre name: %s does not exist", name));
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new GenreException(String.format("More than one genre with the same name %s", name));
        }
    }

    @Override
    public Genre getById(long id) {
        try {
            return jdbc.queryForObject("select id, name from genres where id = :id",
                    Map.of("id", id), new GenreMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new GenreException(String.format("Genre id: %s does not exist", id));
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new GenreException(String.format("More than one genre with the same id %s", id));
        }
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select id, name from genres", new GenreMapper());
    }

    @Override
    public void updateGenre(Genre genre) {
        jdbc.update("update genres set name = :name where id = :id",
                Map.of("id", genre.getId(),
                        "name", genre.getName()));
    }

    @Override
    public void deleteGenre(long id) {
        jdbc.update("delete from genres where id = :id",
                Map.of("id", id));
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
