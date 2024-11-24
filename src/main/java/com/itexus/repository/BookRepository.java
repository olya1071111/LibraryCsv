package com.itexus.repository;

import com.itexus.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAll() throws DaoException;

    void saveBook(Book book) throws DaoException;

    void deleteBookById(int id) throws DaoException;

    void updateBook(Book book) throws DaoException;
}
