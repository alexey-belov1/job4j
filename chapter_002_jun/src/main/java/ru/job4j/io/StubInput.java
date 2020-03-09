package ru.job4j.io;

public class StubInput implements Input {
    private String[] questions;
    private int position = 0;

    public StubInput(String[] questions) {
        this.questions = questions;
    }

    @Override
    public String askStr(String inStr) {
        return questions[position++];
    }

}