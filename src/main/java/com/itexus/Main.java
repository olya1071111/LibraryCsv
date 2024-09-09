package com.itexus;

import com.itexus.config.SpringConfig;
import com.itexus.controller.BookController;
import com.itexus.controller.impl.BookControllerImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookController bookcontroller = context.getBean(BookControllerImpl.class);

        bookcontroller.doAction();

    }
}
