package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ChatTest {
    private String path = System.getProperty("java.io.tmpdir");

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<>() {
        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };

    @Rule
    public TemporaryFolder folder = new TemporaryFolder(new File(path));

    @Test
    public void whenChat() throws IOException {

        File file = folder.newFile("answers.txt");

        try (PrintWriter out = new PrintWriter(file)) {
            out.println("Привет! Я бот");
            out.println("Ничего не знаю");
            out.println("Не отвлекай меня");
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> answers = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(file))) {
            read.lines().forEach(answers::add);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] questions = {
                "Привет",
                "Напиши что-нибудь",
                "Стоп",
                "Напиши что-нибудь",
                "Продолжить",
                "Напиши что-нибудь",
                "Закончить",
                "Пока"
        };
        StubInput input = new StubInput(questions);

        String pathLog = folder.getRoot() + "/chat.log";

        new Chat(input, this.output).chat(file.getPath(), pathLog);

        List<String> result = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(pathLog))) {
            read.lines().forEach(result::add);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(result.get(0), is("User: " + questions[0]));
        assertThat(result.get(2), is("User: " + questions[1]));
        assertThat(result.get(4), is("User: " + questions[2]));
        assertThat(result.get(5), is("User: " + questions[3]));
        assertThat(result.get(6), is("User: " + questions[4]));
        assertThat(result.get(7), is("User: " + questions[5]));
        assertThat(result.get(9), is("User: " + questions[6]));

        int index = result.get(1).indexOf(" ") + 1;
        assertThat(answers.contains(result.get(1).substring(index)), is(true));
        assertThat(answers.contains(result.get(3).substring(index)), is(true));
        assertThat(answers.contains(result.get(8).substring(index)), is(true));

        assertThat(result.size(), is(10));
    }

}
