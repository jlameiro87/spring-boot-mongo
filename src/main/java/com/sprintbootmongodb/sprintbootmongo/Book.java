package com.sprintbootmongodb.sprintbootmongo;

import org.springframework.data.annotation.Id;

public class Book {

    @Id
    public String id;

    public String ISBN;

    public String title;

    public int year;

    public String author;

    public Book() {}

    public Book(String ISBN, String title, int year, String author) {
        this.ISBN = ISBN;
        this.title = title;
        this.year = year;
        this.author = author;
    }

    @Override
    public String toString() {
        return String.format(
                "Book[id='%s', ISBN='%s', title='%s', year='%s', author='%s']",
                id, ISBN, title, year, author);
    }
}
