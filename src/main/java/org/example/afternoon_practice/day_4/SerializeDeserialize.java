package org.example.afternoon_practice.day_4;

import org.example.morning_classes.day_04.Person;

import java.io.*;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class SerializeDeserialize {
    public static final String STUDENTS_DATA = "src/main/java/org/example/afternoon_practice/day_4/students.ser";

    public static void serializeStudents(List<Student> students) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(STUDENTS_DATA);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            for (Student student : students) {
                objectOutputStream.writeObject(student);
            }
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Student> deserializeStudents() {
        try (FileInputStream fileInputStream = new FileInputStream(STUDENTS_DATA);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            try {
                List<Student> loadedStudents = new ArrayList<>();
                while (fileInputStream.available() != 0) {
                    Student student = (Student) objectInputStream.readObject();
                    student.print();
                    loadedStudents.add(student);
                }
                return loadedStudents;
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            } catch ( InvalidClassException e){
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void getGPA() {
        List<Student> students = deserializeStudents();
        if (students != null) {
            double avgGPA = students.stream().mapToDouble(Student::getGPA).average().getAsDouble();
            System.out.println("Average GPA = " + avgGPA);
        }
    }

    public static void doBackupCopy() {
        Path backupFile = Path.of("src/main/java/org/example/afternoon_practice/day_4/students_backup.ser");
        try {
            Files.copy(Path.of(STUDENTS_DATA), backupFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
