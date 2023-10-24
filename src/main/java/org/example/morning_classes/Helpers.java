package org.example.morning_classes;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static void readFromFile(String file, boolean isText){
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            for (byte x: fileInputStream.readAllBytes()) {
                if(isText){
                    System.out.print((char)x);
                }else System.out.print(x);
            }
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        System.out.println("****************************");
    }

    public static void copyFile(String sourceFile) {
        String fileName = sourceFile.substring(sourceFile.lastIndexOf("/")+1,sourceFile.length()-1);
        String destinationFile = sourceFile.replaceAll(fileName, "copy_"+fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);
            fileInputStream.transferTo(fileOutputStream);
            fileOutputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readAndDisplayCSV(String fileCSV, char separator){
        try {
            FileInputStream fileInputStream = new FileInputStream(fileCSV);
            for (byte x: fileInputStream.readAllBytes()){
                if ((char) x=='\n'){
                    System.out.println();
                    System.out.println("---------------------------------------------------------------------");
                }else if ((char) x==separator){
                    System.out.print(" | ");
                }else {
                    System.out.print((char)x);
                }
            }
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readBigFile(String file){
        try  {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
