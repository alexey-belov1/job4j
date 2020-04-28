package ru.job4j.tictactoe;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FieldTest {

    @Test
    public void whenShowField() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Field field = new Field(2);
        field.setMarkX(0, 0);
        field.setMarkO(1, 1);
        field.show();

        String expected = new StringBuilder().append("x ").append("- ").append(System.lineSeparator())
                .append("- ").append("o ").append(System.lineSeparator()).toString();

        assertThat(out.toString(), is(expected));

        System.setOut(stdout);
    }

}
