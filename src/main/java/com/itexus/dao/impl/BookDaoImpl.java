package com.itexus.dao.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.itexus.dao.DaoException;
import com.itexus.model.Book;
import com.itexus.util.GenerateId;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

/**
 * Implementation of the Dao layer: working with the data source file library.csv
 */

@Component
public class BookDaoImpl implements com.itexus.dao.BookDao {
    private final String FILE_CSV_NAME = "src/main/resources/library.csv";
    CsvMapper csvMapper= new CsvMapper();
    @Override
    public void addBook(Book book) throws DaoException {

        List<Book> books = listBooks();

        if (books.size() > 0) {
            GenerateId.checkLastId(books);
            book.setId(GenerateId.nextId());
        }

        books.add(book);

        writeToCsv(books);

    }

    @Override
    public void updateBook(List<Book> books) throws DaoException {

        writeToCsv(books);
    }

    public void writeToCsv(List<Book> books) throws DaoException {

        try {

            csvMapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);

            CsvSchema schema = CsvSchema.builder().setUseHeader(true)
                    .addColumn("id")
                    .addColumn("title")
                    .addColumn("author")
                    .addColumn("description")
                    .build();

            schema = schema.withColumnSeparator(';');

            ObjectWriter writer = csvMapper.writerFor(Book.class).with(schema);

            writer.writeValues(new File(FILE_CSV_NAME)).writeAll(books);

        } catch (FileNotFoundException e) {
            throw new DaoException(e);
        } catch (IOException e) {
            throw new DaoException();
        }

    }

    @Override
    public void deleteBookById(int id) throws DaoException {

        List<Book> books = listBooks();

        for (Book book : books) {
            if (id == book.getId()) {
                books.remove(book);
                break;
            }
        }
        updateBook(books);
    }


    @Override
    public List<Book> listBooks() throws DaoException {

        List<Book> books;

        try {
            Reader myReader = new FileReader(FILE_CSV_NAME);
            CsvMapper mapper = new CsvMapper();

            CsvSchema schema = mapper.schemaFor(Book.class)
                    .withColumnSeparator(';').withSkipFirstDataRow(true);
            MappingIterator<Book> iterator = mapper
                    .readerFor(Book.class)
                    .with(schema)
                    .readValues(myReader);
            books = iterator.readAll();

        } catch (IOException e) {
            throw new DaoException(e);
        }
        return books;
    }
}
