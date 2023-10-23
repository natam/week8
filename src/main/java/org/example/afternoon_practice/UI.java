package org.example.afternoon_practice;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UI {

    public static String getStringInput(Scanner sc, String message) {
        System.out.println(message);
        return sc.nextLine();
    }

    public static String getStringInput(Scanner sc, String regex, String message) {
        System.out.println(message);
        String input = sc.nextLine();
        if (!input.matches(regex)) {
            System.out.println("Not valid email. Please try again");
            return getStringInput(sc, regex, message);
        } else return input;
    }

    public static int getPositiveIntInput(Scanner sc, String message) {
        System.out.println(message);
        int value = 0;
        try {
            value = sc.nextInt();
            sc.nextLine();
            if (value < 0) {
                throw new Exception("Age can not be negative.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return value;
    }

    public static String selectOption(Scanner sc, String message, Map<Integer, String> options) throws Exception {
        System.out.println("Select your favorite color from list: ");
        options.forEach((key, value) -> System.out.println(key + "." + value));
        String input = sc.nextLine();
        if (options.containsValue(input)) {
            return input;
        } else {
            try {
                int key = Integer.parseInt(input);
                if (options.containsKey(key)) {
                    return options.get(key);
                }else {
                    throw new Exception("Color not found in the list");
                }
            } catch (Exception e) {
                throw new Exception("Not valid color");
            }

        }
    }
}
