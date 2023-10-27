package org.example.morning_classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Helpers {
    public static String getUserInput(String message) {
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
        while (!input.equals("Exit")) {
            inputs.add(input);
            fw.append(input).append("\n");
            input = sc.nextLine();
        }
        fw.close();
        return inputs;
    }

    public static void getUserNewUniqueInputs(String outputFile, List<String> previousUserInputs) {
        Scanner sc = new Scanner(System.in);
        List<String> inputs = new ArrayList<>();
        System.out.println("Enter Export to stop input and export");
        try {
            FileWriter fw = new FileWriter(outputFile, true);
            String input = sc.nextLine();
            while (!input.equals("Export")) {
                if (!previousUserInputs.contains(input) && !inputs.contains(input)) {
                    inputs.add(input);
                    fw.append(input).append("\n");
                }
                input = sc.nextLine();
            }
            fw.close();
            sc.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeToFile(String fileName, String textForInput) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            BufferedReader bs = new BufferedReader(new FileReader(fileName));
            if (bs.lines().noneMatch(line -> line.contains(textForInput))) {
                fw.append(textForInput).append("\n");
                fw.close();
            } else System.out.println("Content already presents in file");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int readFileAndGetLines(String fileName) {
        File file = new File(fileName);
        String line;
        int linesCount = 0;
        if (file.exists()) {
            try {
                BufferedReader bs = new BufferedReader(new FileReader(file));
                //linesCount = bs.lines().count();
                while ((line = bs.readLine()) != null) {
                    System.out.println(line);
                    linesCount++;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else System.out.println("File not found");

        return linesCount;
    }


    public static void readFromFile(String file, boolean isText) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            for (byte x : fileInputStream.readAllBytes()) {
                if (isText) {
                    System.out.print((char) x);
                } else System.out.print(x);
            }
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        System.out.println("****************************");
    }

    public static void copyFile(String sourceFile) {
        String fileName = sourceFile.substring(sourceFile.lastIndexOf("/") + 1, sourceFile.length() - 1);
        String destinationFile = sourceFile.replaceAll(fileName, "copy_" + fileName);
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

    public static void copyFileUsingFileReader(String sourceFile) {
        String fileName = sourceFile.substring(sourceFile.lastIndexOf("/") + 1, sourceFile.length() - 1);
        String destinationFile = sourceFile.replaceAll(fileName, "copy_" + fileName);
        StringBuilder str = new StringBuilder();
        int ch;
        try {
            FileReader fileReader = new FileReader(sourceFile);
            while ((ch = fileReader.read()) != -1) {
                str.append((char) ch);
            }
            fileReader.close();
            FileWriter fileWriter = new FileWriter(destinationFile);
            fileWriter.write(str.toString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readAndDisplayCSV(String fileCSV, char separator) {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileCSV);
            for (byte x : fileInputStream.readAllBytes()) {
                if ((char) x == '\n') {
                    System.out.println();
                    System.out.println("---------------------------------------------------------------------");
                } else if ((char) x == separator) {
                    System.out.print(" | ");
                } else {
                    System.out.print((char) x);
                }
            }
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readBigFile(String file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateFile(String inputFile, String outputFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            FileWriter fileWriter = new FileWriter(outputFile);
            String line = reader.readLine();
            while (line != null) {
                line = line.toUpperCase().replaceAll("JAVA", "Python");
                fileWriter.append(line).append("\n");
                line = reader.readLine();
            }
            reader.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readFile(String filePath) {
        File file = new File(filePath);
        int ch;
        if (file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                while ((ch = fileReader.read()) != -1) {
                    System.out.print((char) ch);
                }
                fileReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else System.out.println("File not found");
    }

    public static List<String> getFileContentAsList(String filePath) {
        File file = new File(filePath);
        int ch;
        StringBuilder word = new StringBuilder();
        List<String> words = new ArrayList<>();
        if (file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                while ((ch = fileReader.read()) != -1) {
                    if ((char) ch != '\n') {
                        word.append((char) ch);
                    } else {
                        words.add(word.toString());
                        word.delete(0, word.length());
                    }
                }
                if (!word.isEmpty()) {
                    words.add(word.toString());
                }
                fileReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else System.out.println("File not found");
        return words;
    }

    public static void readNumbersSumAndWrite(String inputFilePath, String outputFilePath) {
        File file = new File(inputFilePath);
        int ch;
        StringBuilder number = new StringBuilder();
        int sum = 0;
        if (file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                while ((ch = fileReader.read()) != -1) {
                    if ((char) ch != '\n') {
                        number.append((char) ch);
                    } else {
                        sum += Integer.parseInt(number.toString());
                        number.delete(0, number.length());
                    }
                }
                if (!number.isEmpty()) {
                    sum += Integer.parseInt(number.toString());
                }
                fileReader.close();
                FileWriter fileWriter = new FileWriter(outputFilePath);
                fileWriter.write(String.valueOf(sum));
                fileWriter.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else System.out.println("File not found");
    }

    public static void printList(List<String> strings) {
        for (String str : strings) {
            System.out.println(str);
        }
    }
}
