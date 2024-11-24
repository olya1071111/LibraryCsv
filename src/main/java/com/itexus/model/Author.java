package com.itexus.model;

import lombok.Data;

@Data
public class Author {
    private int id;
    private String fullName;

    public Author() {
    }

    public Author(String fullName) {
        this.fullName = fullName;
    }

    public Author(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
}
