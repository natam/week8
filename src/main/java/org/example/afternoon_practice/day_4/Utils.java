package org.example.afternoon_practice.day_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
    public static void printStudents(List<Student> students, String message){
        System.out.println(message);
        for (Student student: students){
            student.print();
        }
    }

    public static void createStudensForTestAndSerialize(){
        Student student1 = new Student();
        student1.setName("Natallia");
        student1.setAge(22);
        student1.setAddress("Berlin");
        student1.setGPA(10.0);
        student1.getCourses().put("course1", 6.9);
        student1.setHobbies(Arrays.asList("Dancing","Swimming","Painting"));

        Student student2 = new Student();
        student2.setName("Tom");
        student2.setAge(21);
        student2.setAddress("Berlin");
        student2.setGPA(7.0);
        student2.getCourses().put("course2", 7.9);
        student2.getCourses().put("course1", 6.0);
        student2.setHobbies(Arrays.asList("Swimming","Playing guitar", "Coding"));

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        SerializeDeserialize.serializeStudentsList(students);
    }

}
