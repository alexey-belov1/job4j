package ru.job4j.io;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;

public class Chat {
    private final Input input;
    private final Consumer<String> output;
    private final String stopChat = "Стоп";
    private final String continueChat = "Продолжить";
    private final String endChat = "Закончить";

    public Chat(Input input, Consumer<String> output) {
        this.input = input;
        this.output = output;
    }

    public void chat(String source, String target) {
        List<String> answers = readAnswers(source);

        Random random = new Random();
        boolean workChat = true;
        String question = "";
        List<String> log = new ArrayList<>();

        while (!question.equals(endChat)) {
            question = input.askStr("User: ");
            log.add("User: " + question);

            if (question.equals(endChat) || question.equals(stopChat)) {
                workChat = false;
                continue;
            }

            if (question.equals(continueChat)) {
                workChat = true;
                continue;
            }

            if (workChat) {
                String answer = answers.get(random.nextInt(answers.size()));
                log.add("Bot: " + answer);
                output.accept("Bot: " + answer);
            }
        }

        writeLog(target, log);
    }

    private List<String> readAnswers(String source) {
        List<String> result = null;
        try {
            result = Files.readAllLines(Paths.get(source));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private void writeLog(String target, List<String> log) {
        try {
            Files.write(Paths.get(target), log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Chat(new ConsoleInput(), System.out::println).chat("./server.log", "./chat.log");
    }
}
