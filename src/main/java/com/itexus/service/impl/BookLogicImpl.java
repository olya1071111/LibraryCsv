package com.itexus.service.impl;

import com.itexus.dao.BookDao;
import com.itexus.dao.DaoException;
import com.itexus.model.Book;
import com.itexus.service.BookLogic;
import com.itexus.service.LogicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business logic layer: implementation of the basic commands for the operation of the application
 */

@Service
public class BookLogicImpl implements BookLogic {
    private final BookDao bookDao;

    @Autowired
    public BookLogicImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<Book> getAll() throws LogicException {
        try {
            return bookDao.listBooks();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    @Override
    public void saveBook(Book book) throws LogicException {

        try {
            bookDao.addBook(book);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    @Override
    public void editBook(Book book, Integer id) throws LogicException {
        try {

            List<Book> books = bookDao.listBooks();

            for (Book b : books) {
                if (b.getId().equals(id)) {
                    b.setTitle(book.getTitle());
                    b.setAuthor(book.getAuthor());
                    b.setDescription(book.getDescription());
                }
            }
            bookDao.updateBook(books);
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    public Book findById(int id) throws LogicException {

        Book deleteBook;

        try {
            List<Book> books = bookDao.listBooks();

            for (Book book : books) {
                if (book.getId() == id) {
                    deleteBook = book;
                    return deleteBook;
                }
            }
            throw new LogicException("Not found by given id!");

        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }


    private boolean
    isIdExist(Integer id) throws LogicException {
        Book book = findById(id);
        return book != null;
    }

    @Override
    public void deleteBook(Integer id) throws LogicException {
        try {
            isIdExist(id);
            bookDao.deleteBookById(id);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    @Override
    public Book findByName(String title) throws LogicException {
        try {
            List<Book> books = bookDao.listBooks();

            return books.stream()
                    .filter(p -> p.getTitle().equals(title))
                    .findFirst()
                    .orElse(null);

        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
}
