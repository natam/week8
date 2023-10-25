package org.example.morning_classes;

public class Day3 {
    private static final String INPUT_FILE = "src/main/resources/input.txt";
    private static final String OUTPUT_FILE = "src/main/resources/output.txt";
    private static final String INPUT_NUMBERS_FILE = "src/main/resources/input_numbers.txt";
    private static final String OUTPUT_NUMBERS_FILE = "src/main/resources/output_sum.txt";

    public static void main(String[] args) {
        Helpers.readFile(INPUT_FILE);
        Helpers.writeToFile(OUTPUT_FILE, "My new text for day 3");
        Helpers.copyFile2(INPUT_FILE);
        Helpers.readNumbersSumAndWrite(INPUT_NUMBERS_FILE,OUTPUT_NUMBERS_FILE);
    }
}
