package org.example.afternoon_practice;

public class Book {
    private String title;
    private String author;
    public int publicationYear;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString(){
        return "Title: " + title + " author: " + author + " publication year: " + publicationYear;
    }


}
