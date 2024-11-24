package com.itexus.repository.impl;

import com.itexus.model.Author;
import com.itexus.model.Genre;
import com.itexus.model.Book;
import com.itexus.repository.BookRepository;
import com.itexus.repository.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final JdbcTemplate jdbcTemplate;
    private final GenreRepositoryImpl genreRepository;
    private final AuthorRepositoryImpl authorRepositoryImpl;

    @Autowired
    public BookRepositoryImpl(JdbcTemplate jdbcTemplate, AuthorRepositoryImpl authorRepositoryImpl, GenreRepositoryImpl genreRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.authorRepositoryImpl = authorRepositoryImpl;
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Book> getAll() throws DaoException {

        String sql = "SELECT b.id, b.bookName, b.year, " +
                "a.id AS author_id, a.fullName AS authorName, " +
                "g.id AS genre_id, g.genreName AS genreName " +
                "FROM book b " +
                "JOIN author a ON b.author_id = a.id " +
                "JOIN genre g ON b.genre_id = g.id";

        return jdbcTemplate.query(sql, new RowMapper<>() {

            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Author author = new Author(rs.getInt("author_id"), rs.getString("authorName"));
                Genre genre = new Genre(rs.getInt("genre_id"), rs.getString("genreName"));
                return new Book(rs.getInt("id"), rs.getString("bookName"), rs.getInt("year"), author, genre);
            }
        });
    }

    @Override
    public void saveBook(Book book) throws DaoException {

        /** Находим или создаем автора и жанр */
        int authorId = authorRepositoryImpl.findOrCreateAuthor(book.getAuthor().getFullName());
        int genreId = genreRepository.findOrCreateGenre(book.getGenre().getGenreName());

        /** Сохраняем книгу */
        String sql = "INSERT INTO book (bookName, year, author_id, genre_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, book.getTitle(), book.getYear(), authorId, genreId);
    }

    @Override
    public void deleteBookById(int id) throws DaoException {

        String sql = "DELETE FROM book WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        if (rowsAffected == 0) {
            throw new RuntimeException("Книга с id " + id + " не найдена для удаления");
        }
    }

    @Override
    public void updateBook(Book book) throws DaoException {

        /**Проверка существует ли автор*/
        int authorId = authorRepositoryImpl.findOrCreateAuthor(book.getAuthor().getFullName());

        /**Проверка существует ли жанр*/
        int genreId = genreRepository.findOrCreateGenre(book.getGenre().getGenreName());
        String sql = "UPDATE book SET bookName = ?, year = ?, author_id = ?, genre_id = ? WHERE id = ?";

        jdbcTemplate.update(sql, book.getTitle(), book.getYear(), authorId, genreId, book.getId());
    }

}
