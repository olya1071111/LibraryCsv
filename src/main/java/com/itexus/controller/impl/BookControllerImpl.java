package com.itexus.controller.impl;

import com.itexus.controller.BookController;
import com.itexus.controller.MenuForAddOrUpdateUser;
import com.itexus.service.BookLogic;
import com.itexus.service.LogicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class BookControllerImpl implements BookController {

    private final BookLogic bookLogic;
    String delimiter = "--------------------------------------------------------------------------------------------";

    @Autowired
    public BookControllerImpl(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    private void startMenu() {
        System.out.println("\nMAIN MENU:");
        System.out.println("If you need to add a book, press - 1");
        System.out.println("If you need to see all books, press - 2");
        System.out.println("if you need to delete the book press - 3");
        System.out.println("If you need to change book settings, press - 4");
        System.out.println("If you want to exit press 'E' .");
        System.out.print("Make a choice ---> ");
    }

    public void doAction() throws IOException {

        boolean endWorking = false;
        int id;
        BufferedReader InputNumber = new BufferedReader(new InputStreamReader(System.in));

        while (!endWorking) {
            startMenu();
            String nStr = InputNumber.readLine();
            if (nStr.equals("1")) {
                try {
                    bookLogic.saveBook(MenuForAddOrUpdateUser.DataForAddOrUpdateUser());
                    System.out.println("New book add successfully");
                } catch (LogicException e) {
                    System.out.println("!!! Error adding new book !!!");
                }
                System.out.println(delimiter);
                continue;
            }
            if (nStr.equals("2")) {
                System.out.println("\nList of all books:");
                try {
                    bookLogic.getAll().forEach(System.out::println);
                } catch (LogicException e) {
                    throw new RuntimeException(e + "execution error!");
                }
                System.out.println(delimiter);
                continue;
            }
            if (nStr.equals("3")) {
                System.out.print("\nEnter the Id of the user you want to delete --> ");
                try {
                    id = Integer.parseInt(InputNumber.readLine());
                    bookLogic.deleteBook(id);
                    System.out.println("The book with Id = " + id + " was deleted successfully");
                } catch (LogicException e) {
                    System.out.println("!!! The book was not been deleted! invalid Id number entered !!!");
                }
                System.out.println(delimiter);
                continue;
            }
            if (nStr.equals("4")) {
                System.out.print("\nEnter the Id of the user you want to update --> ");
                try {
                    id = Integer.parseInt(InputNumber.readLine());
                    bookLogic.editBook(MenuForAddOrUpdateUser.DataForAddOrUpdateUser(), id);
                    System.out.println("The book update successfully");
                } catch (LogicException e) {
                    System.out.println("!!! The book was not been update !!!");
                }
                System.out.println(delimiter);
                continue;
            }
            if (nStr.equals("E")) {
                endWorking = true;
            } else {
                System.out.println("Enter the correct number: 1,2,3,4 or E");
                System.out.println();
            }
        }
    }

}

