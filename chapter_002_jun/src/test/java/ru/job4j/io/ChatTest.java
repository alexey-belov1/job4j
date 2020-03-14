package ru.job4j.io;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ChatTest {
    private final String path = System.getProperty("java.io.tmpdir");
    private File file;
    private List<String> answersIn, answersOut;
    private String pathLog;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder(new File(path));

    @Before
    public void init() throws IOException {
        file = folder.newFile("answers.txt");

        answersIn = List.of("Привет! Я бот", "Не отвлекай меня");
        answersOut = answersIn.stream().map(x -> "Bot: " + x).collect(Collectors.toList());

        try {
            Files.write(Paths.get(file.getPath()), answersIn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        pathLog = folder.getRoot() + "/chat.log";
    }

    @Test
    public void whenOneAnswer() {
        String[] questions = {"Привет", "Закончить"};
        List<String> res = new ArrayList<>();
        new Chat(new StubInput(questions), res::add).chat(file.getPath(), pathLog);

        assertThat(answersOut.containsAll(res), is(true));
        assertThat(res.size(), is(1));
    }

    @Test
    public void whenStopAndContinue() {
        String[] questions = {"Стоп", "Напиши что-нибудь", "Продолжить", "Напиши что-нибудь", "Закончить"};
        List<String> res = new ArrayList<>();
        new Chat(new StubInput(questions), res::add).chat(file.getPath(), pathLog);

        assertThat(answersOut.containsAll(res), is(true));
        assertThat(res.size(), is(1));
    }

    @Test
    public void whenWriteLog() {
        String[] questions = {"Привет", "Стоп", "Напиши что-нибудь", "Продолжить", "Напиши что-нибудь", "Закончить"};
        new Chat(new StubInput(questions), x -> { }).chat(file.getPath(), pathLog);

        List<String> outLog = null;
        try {
            outLog = Files.readAllLines(Paths.get(pathLog));
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(outLog.size(), is(8));
    }
}
