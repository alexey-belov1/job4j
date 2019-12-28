package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFullname("Alexey Belov");
        student.setGroup("Mechanics");
        student.setEntry(new Date());
        System.out.println("Student: " + student.getFullname() + "\ngroup: " + student.getGroup() + "\ndate entry: " + student.getEntry());
    }
}