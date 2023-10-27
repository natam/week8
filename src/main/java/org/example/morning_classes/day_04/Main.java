package org.example.morning_classes.day_04;

import java.io.*;

public class Main {
    public static final String SER_FILE = "src/main/java/org/example/morning_classes/day_04/person.ser";
    public static final String SER_VEHICLE_FILE = "src/main/java/org/example/morning_classes/day_04/vehicle.ser";

    public static void main(String[] args) {
        // Serialization
        try (FileOutputStream fileOutputStream = new FileOutputStream(SER_FILE);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            Person person1 = new Person("Natallia", 36);
            objectOutputStream.writeObject(person1);
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileInputStream fileInputStream = new FileInputStream(SER_FILE);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            try {
                Person object = (Person) objectInputStream.readObject();
                System.out.println("Person  test name: " + object.getName()
                        + " person age: " + object.getAge()
                        + " person address: " + object.getAddress()
                        + " current date and time: " + object.getCurrentDateTime());
            }catch (ClassNotFoundException e){
                System.out.println("Error during deserialization to object. Expected another class: " + e.getMessage());
            }catch (InvalidClassException exception){
                System.out.println("Error during deserialization. Classes have different versions. Check if new arguments were introduced or ");
                System.out.println("the class does not have an accessible no-arg constructor:" + exception.getMessage());
                System.out.println(exception.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(SER_VEHICLE_FILE);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            Vehicle vehicle1 = new Vehicle("BMW", "model1", 2020);
            objectOutputStream.writeObject(vehicle1);
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileInputStream fileInputStream = new FileInputStream(SER_VEHICLE_FILE);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Vehicle object = (Vehicle) objectInputStream.readObject();
            System.out.println("Vehicle  make: " + object.getMake()
                    + " vehicle model: " + object.getModel()
                    + " vehicle year: " + object.getYear());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
