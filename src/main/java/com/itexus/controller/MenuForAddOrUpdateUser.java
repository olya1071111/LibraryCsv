package com.itexus.controller;

import com.itexus.model.Book;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MenuForAddOrUpdateUser {
    private MenuForAddOrUpdateUser() {
    }

    public static Book DataForAddOrUpdateUser() {

        Book book = new Book();

        BufferedReader numIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("\nEnter the title book (Ex.: Biology) --> ");

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
                System.out.println("\n The title must consist of letters, and begin with a capital letter.Try again.");
            }
        }

        System.out.print("Enter the name of author (Ex.Ivanov I.I.) --> ");
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
                book.setAuthor(nameOfAuthor);
                isCorrectNameOfAuthor = true;
            } else {
                System.out.println("\n Name of author must consist of letters, and begin with a capital letter.Try again.");
            }
        }

        System.out.print("Enter description of book --> ");
        try {
            book.setDescription(numIn.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return book;
    }
}
