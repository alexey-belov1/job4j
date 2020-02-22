package ru.job4j.analize;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizeTest {
    @Test
    public void whenDiffList() {
        List<Analize.User> previous = List.of(
                new Analize.User(2, "deleted"),
                new Analize.User(3, "notChanged"),
                new Analize.User(4, "notChanged"),
                new Analize.User(5, "changed"),
                new Analize.User(6, "deleted")
        );

        List<Analize.User> current = List.of(
                new Analize.User(1, "added"),
                new Analize.User(3, "notChanged"),
                new Analize.User(4, "notChanged"),
                new Analize.User(5, "changed1"),
                new Analize.User(7, "added"),
                new Analize.User(8, "added")
        );

        Analize.Info info = new Analize().diff(previous, current);
        assertThat(info.added, is(3));
        assertThat(info.changed, is(1));
        assertThat(info.deleted, is(2));
    }
}