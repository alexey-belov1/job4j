package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenPairWithoutComment() {
        String source = "../server.log";
        String target = "../unavailable.csv";
        new Analizy().unavailable(source, target);

        try (BufferedReader read = new BufferedReader(new FileReader(target))) {
            assertThat(read.readLine(), is("10:57:01 10:59:01"));
            assertThat(read.readLine(), is("11:01:02 11:02:02"));
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    @Test
    public void whenUnavailable() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("unavailable.csv");

        try (PrintWriter out = new PrintWriter(source)) {
            out.print("200 10:56:01\n\n"
                    + "500 10:57:01\n\n"
                    + "400 10:58:01\n\n"
                    + "200 10:59:01\n\n"
                    + "500 11:01:02\n\n"
                    + "200 11:02:02");
        }

        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader read = new BufferedReader(new FileReader(target))) {
            read.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:57:01 10:59:01"
                                            + "11:01:02 11:02:02"));
    }
}