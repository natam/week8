package org.example.afternoon_practice;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static String personsOutputFile = "src/main/java/org/example/afternoon_practice/persons.txt";
    public static final Map<Integer, String> colors = new HashMap<>() {{
        put(1, "red");
        put(2, "black");
        put(3, "green");
        put(4, "blue");
        put(5, "white");
    }};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Person person1 = new Person();
        person1.setAge(UI.getPositiveIntInput(sc, "Enter your age: "));
        person1.setName(UI.getStringInput(sc, "Enter your name: "));
        person1.setEmail(UI.getStringInput(sc, "^[a-zA-Z0-9._-]*@[a-z._]*.[a-z]{2,3}", "Enter your email: "));
        person1.setPhone(UI.getStringInput(sc, "Enter your phone number: "));

        System.out.println("Year of birth: " + person1.getBirthYear());

        Book book1 = new Book();
        book1.setTitle(UI.getStringInput(sc, "Enter your favorite book title: "));
        book1.setAuthor(UI.getStringInput(sc, "Enter your favorite book author: "));
        book1.setPublicationYear(UI.getPositiveIntInput(sc, "Enter your favorite book publication year: "));
        person1.addBook(book1);

        try {
            person1.setFavoriteColor(UI.selectOption(sc, "Select your favorite color from list", colors));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        person1.printDetails();

        try {
            FileWriter fileWriter = new FileWriter(personsOutputFile,true);
            fileWriter.append(person1.toString())
                    .append(", address: ")
                    .append(UI.getStringInput(sc, "Enter your address: "))
                    .append("\n");

            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        sc.close();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(personsOutputFile));
            String line = reader.readLine();
            while (line!=null){
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}