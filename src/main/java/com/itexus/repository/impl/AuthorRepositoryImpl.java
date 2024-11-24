package com.itexus.repository.impl;

import com.itexus.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepositoryImpl {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Author> findAll() {
        String sql = "SELECT * FROM author";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Author author = new Author();
            author.setId(rs.getInt("id"));
            author.setFullName(rs.getString("fullName"));
            return author;
        });
    }

    public void save(Author author) {
        String sql = "INSERT INTO author (fullName) VALUES (?)";
        jdbcTemplate.update(sql, author.getFullName());
    }

    public int findOrCreateAuthor(String fullName) {
        try {
            String sql = "SELECT id FROM author WHERE fullName = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{fullName}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            /** Автор не найден, создаем нового*/
            String insertSql = "INSERT INTO author (fullName) VALUES (?) RETURNING id";
            return jdbcTemplate.queryForObject(insertSql, new Object[]{fullName}, Integer.class);
        }
    }

    public void updateAuthor(Author author) {
        String sql = "UPDATE author SET fullName = ? WHERE id = ?";
        jdbcTemplate.update(sql, author.getFullName(), author.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM author WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
