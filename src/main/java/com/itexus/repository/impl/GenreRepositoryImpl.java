package com.itexus.repository.impl;

import com.itexus.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GenreRepositoryImpl {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GenreRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Genre> findAll() {
        String sql = "SELECT * FROM genre";
        return jdbcTemplate.query(sql, new GenreRowMapper());
    }

    public void save(Genre genre) {
        String sql = "INSERT INTO genre (genreName) VALUES (?)";
        jdbcTemplate.update(sql, genre.getGenreName());
    }

    private static class GenreRowMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genre(rs.getInt("id"), rs.getString("genreName"));
        }
    }

    public int findOrCreateGenre(String genreName) {
        try {
            String sql = "SELECT id FROM genre WHERE genreName = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{genreName}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            /** Жанр не найден, создаем новый */
            String insertSql = "INSERT INTO genre (genreName) VALUES (?) RETURNING id";
            return jdbcTemplate.queryForObject(insertSql, new Object[]{genreName}, Integer.class);
        }
    }

    public void updateGenre(Genre genre) {
        String sql = "UPDATE genre SET genreName = ? WHERE id = ?";
        jdbcTemplate.update(sql, genre.getGenreName(), genre.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM genre WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
