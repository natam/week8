package org.example.afternoon_practice.day_4;

import org.example.afternoon_practice.UI;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Students {
    public static final transient String STUDENTS_DATA = "src/main/java/org/example/afternoon_practice/day_4/files/students.ser";
    public static final transient String STUDENTS_REPORT = "src/main/java/org/example/afternoon_practice/day_4/files/students.txt";
    private List<Student> students = new ArrayList<>();

    public Students(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void removeStudent(String query, String fieldToSearch) {
        try {
            Student studentToRemove = searchStudent(query, fieldToSearch).get(0);
            Scanner sc = new Scanner(System.in);
            System.out.println("Deleting student: ");
            studentToRemove.print();
            String userInput = UI.getStringInput(sc, "Are you sure, you want to delete (yes/no)?");
            sc.close();
            if (userInput.equalsIgnoreCase("yes")) {
                students.remove(studentToRemove);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateStudent(String query, String fieldToSearch, String fieldToUpdate, String newValue) {
        try {
            Student studentToUpdate = searchStudent(query, fieldToSearch).get(0);
            System.out.println("Updating student: ");
            studentToUpdate.print();
            switch (fieldToSearch.toLowerCase()) {
                case "name" -> studentToUpdate.setName(newValue);
                case "age" -> studentToUpdate.setAge(Integer.parseInt(newValue));
                case "gpa" -> studentToUpdate.setGPA(Double.parseDouble(newValue));
                default -> throw new Exception("Filed " + fieldToUpdate + " not find in student object");
            }
            ;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Student> searchStudent(String query, String fieldToSearch) throws Exception {
        return switch (fieldToSearch.toLowerCase()) {
            case "name" -> students.stream().filter(student -> student.getName().equalsIgnoreCase(query)).toList();
            case "age" -> students.stream().filter(student -> student.getAge() == Integer.parseInt(query)).toList();
            case "hobby" -> students.stream().filter(student -> student.getHobbies().contains(query)).toList();
            case "course" -> students.stream().filter(student -> student.getCourses().containsKey(query)).toList();
            default -> throw new Exception("Filed " + fieldToSearch + " not find in student object");
        };
    }

    public void groupStudentsByGPA() {
        students.stream()
                .collect(Collectors.groupingBy(Student::getGradeRange))
                .forEach((key, value) -> {
                    System.out.println("Range : " + key);
                    value.forEach(Student::print);
                });
    }

    public void getStudentsGrade(String courseName) {
        System.out.println("Students that take course " + courseName);
        students
                .stream()
                .filter(student -> student.getCourses().containsKey(courseName))
                .forEach(student -> System.out.println("Student name: "
                        + student.getName()
                        + " grade = " + student.getGradeForCourse(courseName)));
    }

    public double getAverageGPA() {
        HashSet<String> hobbies = new HashSet<>();
        double avgGPA = students.stream().mapToDouble(Student::getGPA).average().getAsDouble();
        System.out.println("Average GPA = " + avgGPA);
        return avgGPA;
    }

    public HashSet<String> getHobbies() {
        HashSet<String> hobbies = new HashSet<>();
        students.stream().forEach(student -> hobbies.addAll(student.getHobbies()));
        System.out.println("Students hobbies:");
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }
        return hobbies;
    }

    public void serializeStudentsList() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(STUDENTS_DATA);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(students);
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printStudents() {
        for (Student student : students) {
            student.print();
        }
    }

    public void exportReport() {
        try (FileWriter fileWriter = new FileWriter(STUDENTS_REPORT)) {
            fileWriter.append("Report " + LocalDateTime.now())
                    .append("\n")
                    .append("Students count: ")
                    .append(String.valueOf(students.size()))
                    .append("\n")
                    .append("Average students GPA: ")
                    .append(String.valueOf(getAverageGPA()))
                    .append("\n")
                    .append("Students hobbies: ")
                    .append(String.join(", ", getHobbies()))
                    .append("\n")
                    .append("Students: ")
                    .append("\n");
            for (Student student : students) {
                fileWriter.append(student.toString()).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
