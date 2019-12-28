package ru.job4j.inheritance;

public class JSONReport extends TextReport {
    public String generate(String name, String body) {
        return String.format("{\n name: %s \n body: %s \n}", name, body);
    }
}
