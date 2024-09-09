package com.itexus.model.dao;

import com.itexus.model.Book;

import java.util.List;

public interface BookDao {
    void addBook(Book book) throws DaoException;

    void updateBook(List<Book> books) throws DaoException;

    void deleteBookById(int id) throws DaoException;

    List<Book> listBooks() throws DaoException;
}
