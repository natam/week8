package org.example.morning_classes;

import java.util.List;

public class Day3 {
    private static final String INPUT_FILE = "src/main/resources/input.txt";
    private static final String OUTPUT_FILE = "src/main/resources/output.txt";
    private static final String OUTPUT_FILE_DAY3 = "src/main/resources/output_day3.txt";
    private static final String INPUT_NUMBERS_FILE = "src/main/resources/input_numbers.txt";
    private static final String OUTPUT_NUMBERS_FILE = "src/main/resources/output_sum.txt";

    public static void main(String[] args) {
        //Exercises from presentation
        Helpers.readFile(INPUT_FILE);
        Helpers.writeToFile(OUTPUT_FILE, "My new text for day 3");
        Helpers.copyFile2(INPUT_FILE);
        Helpers.readNumbersSumAndWrite(INPUT_NUMBERS_FILE,OUTPUT_NUMBERS_FILE);

        //Exercise: Text Collector with Uniqueness
        List<String> previousInputs = Helpers.getFileContentAsList(OUTPUT_FILE_DAY3);
        Helpers.printList(previousInputs);
        //Helpers.getUserNewUniqueInputs(OUTPUT_FILE_DAY3, previousInputs);

        //Exercise 2: Line Counter
        int lines = Helpers.readFileAndGetLines(INPUT_FILE);
        System.out.println("Lines in file: " + lines);

        //Exercise 3: Convert to Uppercase
        Helpers.updateFile(INPUT_FILE, OUTPUT_FILE);

    }
}
