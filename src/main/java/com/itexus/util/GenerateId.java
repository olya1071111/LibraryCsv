package com.itexus.util;


import com.itexus.model.Book;

import java.util.List;

public class GenerateId {

    private GenerateId() {
    }

    private static int nextId = 1;

    public static int nextId() {
        return nextId++;
    }

    public static void checkLastId(List<Book> book) {
        if (book.size() > 0) {
            int size = book.size();
            int id = book.get(size - 1).getId();
            nextId = id + 1;
        }
    }
}
