package org.example.afternoon_practice;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class UserInteraction {
    static Scanner sc = new Scanner(in);

    public static void runUI(){
        out.println("Welcome to Application");
        printCommandOptions();
        String command = sc.nextLine();
        while (!command.equals("exit")){
            readAndImplementCommand(command);
            command = printAndReadOutput("Enter the command, you would like to implement: ");
        }

    }
    public static void readAndImplementCommand(String command){

        switch (command) {
            case "1":
                PersonCSVExport.addUserToCSV(printAndReadOutput("Add a new user entry (Name;Age;Email;Phone;Address):"));
                break;
            case "2":
                int columnIndex = Integer.parseInt(printAndReadOutput("Please enter 0 if you will search by name, 2 - by email or 3 - by phone number:"));
                String query = printAndReadOutput("Enter value to search: ");
                PersonCSVExport.printUsers(PersonCSVExport.searchUser(query, columnIndex));
                break;
            case "3":
                String userName = printAndReadOutput("Enter user name you want to update: ");
                String columnName = printAndReadOutput("Please enter field you want to update from following name/age/email/phone/address:");
                String newValue = printAndReadOutput("Enter new value for field: ");
                PersonCSVExport.updateUser(userName, newValue, PersonCSVExport.getColumnIndex(columnName));
                break;
            case "4":
                userName = printAndReadOutput("Enter user name you want to remove: ");
                PersonCSVExport.removeUser(userName, 0);
                break;
            case "5":
                columnIndex = Integer.parseInt(printAndReadOutput("Please enter 0 if you want to sort by name or 1 if by age:"));
                PersonCSVExport.sortUsers(columnIndex);
                break;
            case "6":
                PersonCSVExport.printUsers(PersonCSVExport.getUsersFromCSV());
                break;
            case "7":
                PersonCSVExport.getUsersNumberInCSV();
                break;
            case "exit":
                break;
            default:
                out.println("Command is not defined.");
                printCommandOptions();
        }
    }

    public static String printAndReadOutput(String message){
        out.println(message);
        String userInput = sc.nextLine();;
        return userInput;
    }

    public static void printCommandOptions(){
        out.println("Select command you would like to implement from following:");
        out.println("1 - add a new user");
        out.println("2 - search user");
        out.println("3 - update user");
        out.println("4 - remove user");
        out.println("5 - sort users");
        out.println("6 - display users");
        out.println("7 - display users number in file");
        out.println("exit - exit the application");
    }
}
