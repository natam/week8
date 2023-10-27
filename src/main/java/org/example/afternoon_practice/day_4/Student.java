package org.example.afternoon_practice.day_4;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Student implements Serializable {
    public static final transient String STUDENTS_DATA = "src/main/java/org/example/afternoon_practice/day_4/students.ser";
    private String name;
    private int age;
    private String address;
    private double GPA;
    Map<String, Double> courses = new HashMap<>();
    List<String> hobbies = new ArrayList<>();

    public String getName() {
        return name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public Map<String, Double> getCourses() {
        return courses;
    }

    public void setCourses(Map<String, Double> courses) {
        this.courses = courses;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public void serialize() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(STUDENTS_DATA);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(this);
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void print() {
        System.out.println("Student name: " + name
                + " student age: " + age
                + " student address: " + address
                + " student GPA: " + GPA
                + " student courses: " + courses
                    .entrySet()
                    .stream()
                    .map(entry -> entry.getKey() + " - " + entry.getValue())
                    .collect(Collectors.joining(","))
                + " student hobbies: " + String.join(",", hobbies));
    }

    public Student deserialize() {
        try (FileInputStream fileInputStream = new FileInputStream(STUDENTS_DATA);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            try {
                Student loadedStudent = (Student) objectInputStream.readObject();
                System.out.println("Student name name: " + loadedStudent.getName()
                        + " student age: " + loadedStudent.getAge()
                        + " student address: " + loadedStudent.getAddress()
                        + " student GPA: " + loadedStudent.getGPA()
                        + " student courses: " + loadedStudent
                        .getCourses()
                        .entrySet()
                        .stream()
                        .map(entry -> entry.getKey() + " - " + entry.getValue())
                        .collect(Collectors.joining(",")));
                return loadedStudent;
            } catch (ClassNotFoundException e) {
                System.out.println("Error during deserialization to object. Expected another class: " + e.getMessage());
            } catch (InvalidClassException exception) {
                System.out.println("Error during deserialization. Classes have different versions. Check if new arguments were introduced or ");
                System.out.println("the class does not have an accessible no-arg constructor:" + exception.getMessage());
                System.out.println(exception.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
