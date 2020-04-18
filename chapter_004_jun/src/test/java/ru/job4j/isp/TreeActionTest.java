package ru.job4j.isp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TreeActionTest {

    @Test
    public void whenAddActionAndSelect() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Tree tree = new Tree("Task 1");
        tree.add("Task 1", "Task 1.1");
        tree.add("Task 1", "Task 1.2");
        tree.add("Task 1.1", "Task 1.1.1");

        TreeAction treeAction = new TreeAction(tree);
        treeAction.addAction("Task 1.1", () -> System.out.print("Test1"));

        treeAction.select("Task 1.1");
        assertThat(out.toString(), is("Test1"));

        out.reset();
        treeAction.select("Task 1.1.1");
        assertThat(out.toString(), is(""));

        System.setOut(stdout);
    }
}
