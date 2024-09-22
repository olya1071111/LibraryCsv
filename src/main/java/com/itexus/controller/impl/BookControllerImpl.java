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
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class BookControllerImpl implements BookController {

    private final BookLogic bookLogic;
    String delimiter = "--------------------------------------------------------------------------------------------";

    @Autowired
    public BookControllerImpl(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    private void startMenu(ResourceBundle rb) {
        System.out.println(rb.getString("menu"));
        System.out.println(rb.getString("choice1"));
        System.out.println(rb.getString("choice2"));
        System.out.println(rb.getString("choice3"));
        System.out.println(rb.getString("choice4"));
        System.out.println(rb.getString("choice5"));
        System.out.print(rb.getString("make_choice"));
    }

    public void doAction() throws IOException {

        Locale locale = new Locale("en");
        ResourceBundle rb = ResourceBundle.getBundle("text",locale);

        boolean endWorking = false;
        int id;
        BufferedReader InputNumber = new BufferedReader(new InputStreamReader(System.in));

        while (!endWorking) {
            startMenu(rb);
            String nStr = InputNumber.readLine();
            if (nStr.equals("1")) {
                try {
                    bookLogic.saveBook(MenuForAddOrUpdateUser.DataForAddOrUpdateUser());
                    System.out.println(rb.getString("add_book"));
                } catch (LogicException e) {
                    System.out.println(rb.getString("err_add"));
                }
                System.out.println(delimiter);
                continue;
            }
            if (nStr.equals("2")) {
                System.out.println(rb.getString("all_books"));
                try {
                    bookLogic.getAll().forEach(System.out::println);
                } catch (LogicException e) {
                    throw new RuntimeException(e + rb.getString("err_all"));
                }
                System.out.println(delimiter);
                continue;
            }
            if (nStr.equals("3")) {
                System.out.print(rb.getString("id_del"));
                try {
                    id = Integer.parseInt(InputNumber.readLine());
                    bookLogic.deleteBook(id);
                    System.out.println(rb.getString("del_book1") + id + rb.getString("del_book2"));
                } catch (LogicException e) {
                    System.out.println(rb.getString("err_del"));
                }
                System.out.println(delimiter);
                continue;
            }
            if (nStr.equals("4")) {
                System.out.print(rb.getString("id_up"));
                try {
                    id = Integer.parseInt(InputNumber.readLine());
                    bookLogic.editBook(MenuForAddOrUpdateUser.DataForAddOrUpdateUser(), id);
                    System.out.println(rb.getString("up_book"));
                } catch (LogicException e) {
                    System.out.println(rb.getString("err_up"));
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

