package com.itexus.service.impl;

import com.itexus.model.Book;
import com.itexus.repository.BookRepository;
import com.itexus.repository.DaoException;
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
    //    private final BookDao bookDao;
    private final BookRepository bookRepository;

    @Autowired
    public BookLogicImpl(BookRepository bookRepository) {
//        this.bookDao = bookDao;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAll() throws LogicException {
        try {
            //  return bookDao.listBooks();
            return bookRepository.getAll();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    @Override
    public void saveBook(Book book) throws LogicException {

        try {
            bookRepository.saveBook(book);
            //    bookDao.addBook(book2);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    @Override
    public void editBook(Book book, Integer id) throws LogicException {
        try {

            book.setId(id);

//            List<Book2> book2s = bookDao.listBooks();
//
//            for (Book2 b : book2s) {
//                if (b.getId().equals(id)) {
//                    b.setTitle(book2.getTitle());
//                    b.setAuthor(book2.getAuthor());
//                    b.setDescription(book2.getDescription());
//                }
//            }
            //           bookDao.updateBook(book2s);
            bookRepository.updateBook(book);
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

//    public Book2 findById(int id) throws LogicException {
//
//        Book2 deleteBook2;
//
//        try {
//            List<Book2> book2s = bookDao.listBooks();
//
//            for (Book2 book2 : book2s) {
//                if (book2.getId() == id) {
//                    deleteBook2 = book2;
//                    return deleteBook2;
//                }
//            }
//            throw new LogicException("Not found by given id!");
//
//        } catch (DaoException1 e) {
//            throw new LogicException(e);
//        }
//    }


//    private boolean
//    isIdExist(Integer id) throws LogicException {
//        Book2 book2 = findById(id);
//        return book2 != null;
//    }

    @Override
    public void deleteBook(Integer id) throws LogicException {
        try {
//            isIdExist(id);
//            bookDao.deleteBookById(id);
            bookRepository.deleteBookById(id);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    @Override
    public Book findByName(String title) throws LogicException {
        try {
            List<Book> books = bookRepository.getAll();

            return books.stream()
                    .filter(p -> p.getTitle().contains(title))
                    .findFirst()
                    .orElse(null);

        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
}
