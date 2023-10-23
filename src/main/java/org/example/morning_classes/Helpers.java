package org.example.morning_classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Helpers {
    public static String getUserInput(String message){
        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        return sc.nextLine();
    }

    public static List<String> getUserInputs() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Exit to stop input ");
        FileWriter fw = new FileWriter("src/main/resources/userInputs.txt");
        List<String> inputs = new ArrayList<>();
        String input = sc.nextLine();
        while (!input.equals("Exit")){
            inputs.add(input);
            fw.append(input).append("\n");
            input = sc.nextLine();
        }
        fw.close();
        return inputs;
    }

    public static void writeToFile(String fileName, String textForInput) throws IOException {
        FileWriter fw = new FileWriter(fileName, true);
        BufferedReader bs = new BufferedReader(new FileReader(fileName));
        if(bs.lines().noneMatch(line -> line.contains(textForInput))){
            fw.append(textForInput).append("\n");
            fw.close();
        }else System.out.println("Content already presents in file");

    }
}
