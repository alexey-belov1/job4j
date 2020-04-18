package ru.job4j.isp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TreeTest {

    @Test
    public void whenAddAndFindBy() {
        Tree tree = new Tree("Task 1");
        tree.add("Task 1", "Task 1.1");

        assertThat(tree.findBy("Task 1").get().getName(), is("Task 1"));
        assertThat(tree.findBy("Task 1.1").get().getName(), is("Task 1.1"));
        assertThat(tree.findBy("Task 2").isPresent(), is(false));
    }

    @Test
    public void whenShow() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Tree tree = new Tree("Task 1");
        tree.add("Task 1", "Task 1.1");
        tree.add("Task 1", "Task 1.2");
        tree.add("Task 1.1", "Task 1.1.1");
        tree.show();

        String expected = new StringBuilder().append("Task 1").append(System.lineSeparator())
                .append("-Task 1.1").append(System.lineSeparator())
                .append("--Task 1.1.1").append(System.lineSeparator())
                .append("-Task 1.2").append(System.lineSeparator()).toString();

        assertThat(out.toString(), is(expected));

        System.setOut(stdout);
    }
}