package ru.job4j.io;

import java.io.*;
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

        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {

            Random random = new Random();
            boolean workChat = true;
            String question = "";

            while (!question.equals(endChat)) {
                question = input.askStr("User: ");
                out.println("User: " + question);

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
                    out.println("Bot: " + answer);
                    output.accept("Bot: " + answer);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> readAnswers(String source) {
        List<String> result = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(new File(source)))) {
            String line;
            while ((line = read.readLine()) != null) {
                result.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        new Chat(new ConsoleInput(), System.out::println).chat("./server.log", "./chat.log");
    }
}
