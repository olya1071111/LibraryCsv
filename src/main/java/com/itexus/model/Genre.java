package com.itexus.model;

import lombok.Data;

@Data
public class Genre {
    private int id;
    private String genreName;

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public Genre(int id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }
}
