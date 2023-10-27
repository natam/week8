package org.example.afternoon_practice.day_4;

import java.util.Arrays;

public class Main5 {
    public static void main(String[] args) {
        Utils.createStudensForTestAndSerialize();
        Students studentsList = new Students(SerializeDeserialize.deserializeStudentsList());

        studentsList.printStudents();
        studentsList.getStudentsGrade("course1");
        try {
            Utils.printStudents(studentsList.searchStudent("22", "age"), "Search result: ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        studentsList.groupStudentsByGPA();
        studentsList.getAverageGPA();
        studentsList.getHobbies();
        studentsList.updateStudent("Tom", "name", "name", "Tom2");
        studentsList.printStudents();
        studentsList.serializeStudentsList();
        studentsList.exportReport();
        studentsList.removeStudent("Tom2", "name");
        studentsList.serializeStudentsList();
        Utils.printStudents(SerializeDeserialize.deserializeStudentsList(Students.STUDENTS_DATA), "Deserialized students");
    }
}
