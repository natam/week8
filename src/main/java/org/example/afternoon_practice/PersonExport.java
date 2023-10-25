package org.example.afternoon_practice;

import org.example.morning_classes.Helpers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonExport {
    public static String personsFile = "src/main/java/org/example/afternoon_practice/persons.txt";

    public static void exportPerson(Person person){
        try {
            FileWriter fileWriter = new FileWriter(personsFile, true);
            fileWriter.append(person.toString())
                    .append("\n");
            fileWriter.close();
            Log.addLog("Edit file");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static String getPersonData(String userName, String regex) throws Exception {
        String userData="";
        File fileToRead = new File(personsFile);
        if(!fileToRead.exists()){
            throw new Exception("File not exist");
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(personsFile));
            String line = reader.readLine();

            Pattern pattern = Pattern.compile(regex);
            while (line!=null){
                if(line.contains(userName)){
                    Matcher matcher = pattern.matcher(line);
                    if(matcher.find()){
                        userData = matcher.group();
                        break;
                    }
                }
                line = reader.readLine();
            }
            reader.close();
            Log.addLog("Read file");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return userData;
    }

    public static void updatePersonData(String personName, String newDate, String regex){
        String nameData = "Name: " + personName + ";";
        List<String> lines = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(personsFile));
            String line = reader.readLine();
            while (line!=null){
                if(line.contains(nameData)){
                    line = line.replaceAll(regex, newDate);
                }
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
            FileWriter fileWriter = new FileWriter(personsFile);
            for (String str: lines) {
                fileWriter.append(str).append("\n");
            }
            fileWriter.close();
            Log.addLog("Update file");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removePersonData(String personName){
        String nameData = "Name: " + personName + ";";
        List<String> lines = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(personsFile));
            String line = reader.readLine();
            while (line!=null){
                if(!line.contains(nameData)){
                    lines.add(line);
                }
                line = reader.readLine();
            }
            reader.close();
            FileWriter fileWriter = new FileWriter(personsFile);
            for (String str: lines) {
                fileWriter.append(str).append("\n");
            }
            fileWriter.close();
            Log.addLog("Remove from file");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removePersonDataWithConfirmation(String personName, Scanner sc){
        String nameData = "Name: " + personName + ";";
        List<String> lines = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(personsFile));
            String line = reader.readLine();
            while (line!=null){
                if(!line.contains(nameData)){
                    lines.add(line);
                }else {
                    String answer = UI.getStringInput(sc, "Do you want to remove your data? (yes/no): ");
                    if(answer.toLowerCase().equals("no")){
                        lines.add(line);
                        Log.addLog("Read file");
                    }else {
                        Log.addLog("Remove from file");
                    }
                }
                line = reader.readLine();
            }
            reader.close();
            FileWriter fileWriter = new FileWriter(personsFile);
            for (String str: lines) {
                fileWriter.append(str).append("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addPersonAdditionalData(String personName, String dataToAdd){
        String nameData = "Name: " + personName + ";";
        List<String> lines = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(personsFile));
            String line = reader.readLine();
            while (line!=null){
                if(line.contains(nameData)){
                    line = line.concat("; ").concat(dataToAdd);
                }
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
            FileWriter fileWriter = new FileWriter(personsFile);
            for (String str: lines) {
                fileWriter.append(str).append("\n");
            }
            fileWriter.close();
            Log.addLog("Update file");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readAndDisplayPersonsData(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(personsFile));
            String line = reader.readLine();
            while (line!=null){
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
            Log.addLog("Read file");
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
