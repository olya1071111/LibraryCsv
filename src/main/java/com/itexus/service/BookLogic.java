package com.itexus.service;

import com.itexus.model.Book;

import java.util.List;

public interface BookLogic {
    List<Book> getAll() throws LogicException;

    void saveBook(Book book) throws LogicException;

    void editBook(Book book, Integer in) throws LogicException;

    void deleteBook(Integer id) throws LogicException;


}
