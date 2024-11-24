package com.itexus.controller;

import com.itexus.model.Author;
import com.itexus.model.Book;
import com.itexus.model.Genre;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MenuForAddOrUpdateUser {

    private MenuForAddOrUpdateUser() {
    }

    public static Book DataForAddOrUpdateUser(ResourceBundle rb) {

        Book book = new Book();

        BufferedReader numIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(rb.getString("title_book"));

        boolean isCorrectTitle = false;
        while (!isCorrectTitle) {
            String title;
            try {
                title = numIn.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Pattern pattern = Pattern.compile("[A-ZА-Я][a-zа-я]+");
            Matcher matcher = pattern.matcher(title);
            if (matcher.matches()) {
                book.setTitle(title);
                isCorrectTitle = true;
            } else {
                System.out.println(rb.getString("err_title"));
            }
        }

        System.out.print(rb.getString("author"));
        boolean isCorrectNameOfAuthor = false;
        while (!isCorrectNameOfAuthor) {
            String nameOfAuthor;
            try {
                nameOfAuthor = numIn.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Pattern pattern = Pattern.compile("[A-ZА-Я][a-zа-я]+");
            Matcher matcher = pattern.matcher(nameOfAuthor);
            if (matcher.matches()) {
                Author author = new Author(nameOfAuthor);
                book.setAuthor(author);
                isCorrectNameOfAuthor = true;
            } else {
                System.out.println(rb.getString("err_author"));
            }
        }

        System.out.print(rb.getString("genre"));
        try {
            Genre genre = new Genre(numIn.readLine());
            book.setGenre(genre);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.print(rb.getString("year"));
        try {
            book.setYear(Integer.parseInt(numIn.readLine()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return book;
    }
}
