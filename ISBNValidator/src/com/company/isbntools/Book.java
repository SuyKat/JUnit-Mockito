package com.company.isbntools;

public class Book {
    private String isbn;
    private String title;
    private String auther;

    public Book(String isbn, String title, String auther) {
        this.isbn = isbn;
        this.title = title;
        this.auther = auther;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuther() {
        return auther;
    }
}
