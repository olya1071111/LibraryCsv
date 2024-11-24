package com.itexus.model;

import lombok.Data;

@Data
public class Book {
    private int id;
    private String title;
    private int year;
    private Author author;
    private Genre genre;

    public Book() {
    }

    public Book(int id, String title, int year, Author author, Genre genre) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book:" +
                "id=" + id +
                "| title='" + title + '\'' +
                "| year=" + year +
                "| author=" + author.getFullName() +
                "| genre=" + genre.getGenreName() +
                '|';
    }
}
