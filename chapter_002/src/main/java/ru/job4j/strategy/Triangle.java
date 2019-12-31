package ru.job4j.strategy;

public class Triangle implements Shape {
    @Override
    public String draw() {
        return new StringBuilder()
                .append("  +  ")
                .append(" + + ")
                .append("+++++").toString();
    }
}
