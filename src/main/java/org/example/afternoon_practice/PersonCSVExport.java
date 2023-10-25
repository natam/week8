package org.example.afternoon_practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PersonCSVExport {
    public static final String USERS_FILE = "src/main/java/org/example/afternoon_practice/users.csv";

    public static void addUserToCSV(String personData) {
        try (FileWriter fileWriter = new FileWriter(USERS_FILE, true);){
            fileWriter.append(personData);
            fileWriter.append('\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void exportPersons(List<Person> users) {
        List<String> usersStr = users.stream().map(Person::toString2).toList();
        exportUsers(usersStr, false);
    }

    public static List<String> getUsersFromCSV() {
        List<String> users = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE));){
            String line = reader.readLine();
            while (line != null) {
                users.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public static void printUsers(List<String> users){
        for (String user:users) {
            System.out.println(user);
        }
    }

    public static void getUsersNumberInCSV(){
        int usersNumber = getUsersFromCSV().size();
        System.out.println("CSV File contains " + usersNumber+ " users.");
    }

    public static void exportUsers(List<String> users, Boolean isAppendAllowed) {
        try (FileWriter fileWriter = new FileWriter(USERS_FILE, isAppendAllowed);){
            for (String user : users) {
                fileWriter.append(user);
                fileWriter.append('\n');
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeUser(String query, int columnIndex) {
        List<String> users = getUsersFromCSV();
        for (String user : users) {
            String[] userArr = user.split(";");
            if (userArr[columnIndex].equalsIgnoreCase(query)) {
                users.remove(user);
                break;
            }
        }
        exportUsers(users, false);
    }

    public static void updateUser(String userName, String newData, int columnIndexToUpdate) {
        List<String> users = getUsersFromCSV();
        for (String user : users) {
            String[] userArr = user.split(";");
            if (userArr[0].equalsIgnoreCase(userName)) {
                String updatedUser = user.replaceAll(userArr[columnIndexToUpdate], newData);
                users.set(users.indexOf(user), updatedUser);
                System.out.println("User was updated");
                System.out.println(updatedUser);
                break;
            }
        }
        exportUsers(users, false);
    }

    public static List<String> searchUser(String query, int columnIndex) {
        List<String> users = getUsersFromCSV();
        return users.stream()
                .map(user -> user.split(";"))
                .filter(userArray -> userArray[columnIndex].equalsIgnoreCase(query))
                .map(userArray -> String.join(";", userArray))
                .toList();
    }

    public static void sortUsers(int columnIndexToSortBy) {
        List<String> users = getUsersFromCSV();
        users = users.stream()
                .map(user -> user.split(";"))
                .sorted(Comparator.comparing(userArray -> userArray[columnIndexToSortBy]))
                .map(userArray -> String.join(";", userArray))
                .toList();
        exportUsers(users, false);
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
