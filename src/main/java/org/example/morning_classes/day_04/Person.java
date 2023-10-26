package org.example.morning_classes.day_04;

import java.io.*;
import java.time.LocalDateTime;

public class Person implements Externalizable {
    private String name;
    private int age;
    private String address;
    private transient LocalDateTime currentDateTime;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.currentDateTime = LocalDateTime.now();
    }

    public Person() {
    }

    public Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.currentDateTime = LocalDateTime.now();
    }

    public LocalDateTime getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(LocalDateTime currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(new StringBuilder(name).reverse().toString());
        out.writeInt(age);
        if(address==null){
            out.writeUTF("not provided");
        }else {
            out.writeUTF(address);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = new StringBuilder(in.readUTF()).reverse().toString();
        age = in.readInt();
        address = in.readUTF();
    }
}
