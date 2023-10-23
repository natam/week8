package org.example.morning_classes;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {
    public static void main(String[] args) {
        //String userInputStr = Helpers.getUserInput("Please enter your name: ");
        //System.out.printf("Hello %s! Nice to meet you!", userInputStr);

        try {
            FileReader rf = new FileReader("src/main/resources/input.txt");
            FileWriter fw = new FileWriter("src/main/resources/output.txt");
            rf.transferTo(fw);
            fw.close();
            rf.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/source.txt");

            FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/destination.txt");
            fileInputStream.transferTo(fileOutputStream);
            fileInputStream.close();
            fileOutputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/data.csv"));
            List<String[]> results = bufferedReader.lines().map(line -> line.split(",")).toList();
            for (String[] line: results) {
                for (String str: line) {
                    System.out.println(str);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/source.txt"));
            List<String> words= new ArrayList<>();
            String line = br.readLine();
            while (line!=null){
                words.addAll(Arrays.asList(line.split("\s")));
                line = br.readLine();
            }
            System.out.println(words.size());
            String wordsF = words.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();

            System.out.println(wordsF);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Helpers.writeToFile("src/main/resources/output2.txt", "nkh new text");
            Helpers.writeToFile("src/main/resources/output2.txt", "nkh new text");
            Helpers.writeToFile("src/main/resources/output2.txt", "new line");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
