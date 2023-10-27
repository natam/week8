package org.example.afternoon_practice.day_4;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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

    public static void serializeStudentsList(List<Student> students) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(STUDENTS_DATA);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(students);
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Student> deserializeStudentsList() {
        try (FileInputStream fileInputStream = new FileInputStream(STUDENTS_DATA);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            try {
                return (List<Student>) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InvalidClassException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Student> deserializeStudentsList(String file) {
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            try {
                return (List<Student>) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InvalidClassException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InvalidClassException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void getHobbiesAndAverageGPA() {
        List<Student> students = deserializeStudentsList();
        HashSet<String> hobbies = new HashSet<>();
        if (students != null) {
            double avgGPA = students.stream().mapToDouble(Student::getGPA).average().getAsDouble();
            System.out.println("Average GPA = " + avgGPA);
            students.stream().forEach(student -> hobbies.addAll(student.getHobbies()));
            System.out.println("Students hobbies:");
            for (String hobby: hobbies){
                System.out.println(hobby);
            }
        }
    }

    public static void getStudentsGrade(String courseName) {
        List<Student> students = deserializeStudentsList();
        if (students != null) {
            System.out.println("Students that take course " + courseName);
            students
                    .stream()
                    .filter(student -> student.getCourses().containsKey(courseName))
                    .forEach(student -> System.out.println("Student name: "
                            + student.getName()
                            + " grade = " + student.getGradeForCourse(courseName)));
        }
    }

    public static void groupStudentsByGPA() {
        List<Student> students = deserializeStudentsList();
        if (students != null) {
            //System.out.println("Students that take course " + courseName);
            students
                    .stream()
                    .collect(Collectors.groupingBy(Student::getGradeRange))
                    .forEach((key, value) -> {
                        System.out.println("Range : " + key);
                        value.forEach(Student::print);
                    });
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
