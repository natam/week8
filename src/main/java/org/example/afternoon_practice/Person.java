package org.example.afternoon_practice;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Person {
    private String name;
    private int age;
    private String email;
    private String phone;
    private List<Book> favoriteBooks = new ArrayList<>();
    private String favoriteColor;

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getFavoriteColor() {
        return favoriteColor;
    }

    public void setFavoriteColor(String favoriteColor) {
        this.favoriteColor = favoriteColor;
    }
    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, String email, String phone) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void printDetails(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name: ")
                .append(name)
                .append(" age: ")
                .append(age)
                .append(" email: ")
                .append(email)
                .append(" phone: ")
                .append(phone)
                .append("; favorite color: ")
                .append(favoriteColor)
                .append("; favorite books: ")
                .append(booksToString());
        System.out.println(stringBuilder);
    }

    @Override
    public String  toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name: ")
                .append(name)
                .append("; age: ")
                .append(age)
                .append("; email: ")
                .append(email)
                .append("; phone: ")
                .append(phone)
                .append("; favorite color: ")
                .append(favoriteColor)
                .append("; favorite books: ")
                .append(booksToString());
        return stringBuilder.toString();
    }

    public String  toString2(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name)
                .append(";")
                .append(age)
                .append(";")
                .append(email)
                .append(";")
                .append(phone)
                .append(";")
                .append(address);
        return stringBuilder.toString();
    }

    public int getBirthYear(){
        Date currentDate = new Date();
        return currentDate.getYear()-age;
    }

    public void addBook(Book book){
        favoriteBooks.add(book);
    }

    public String booksToString(){
        StringBuilder booksBuilder = new StringBuilder();
        return favoriteBooks.stream().map(Book::toString).collect(Collectors.joining(","));
    }

    public void exportMyData(String file){
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append(this.toString())
                    .append("\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getColumnIndex(String columnName){
        int columnIndex=0;
        switch (columnName.toLowerCase()){
            case "name":
               columnIndex = 0;
               break;
            case "age":
                columnIndex = 1;
                break;
            case "email":
                columnIndex = 2;
                break;
            case "phone":
                columnIndex = 3;
                break;
            case "address":
                columnIndex = 4;
                break;
        }
        return columnIndex;
    }
}
