package org.example.morning_classes;

public class Day2 {
    private static final String INPUT_FILE = "src/main/resources/input.txt";
    private static final String INPUT_FILE_3 = "src/main/resources/input_3.txt";
    private static final String OUTPUT_FILE_3 = "src/main/resources/output_3.txt";
    private static final String IMAGE_FILE = "src/main/resources/image.jpg";
    private static final String CSV_FILE = "src/main/resources/data.csv";
    public static void main(String[] args) {
        Helpers.readFromFile(INPUT_FILE, true);
        Helpers.readFromFile(IMAGE_FILE, false);
        Helpers.copyFile(IMAGE_FILE);
        Helpers.readAndDisplayCSV(CSV_FILE, ',');
        Helpers.updateFile(INPUT_FILE_3, OUTPUT_FILE_3);
    }
}
