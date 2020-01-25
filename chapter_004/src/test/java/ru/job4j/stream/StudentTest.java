package ru.job4j.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StudentTest {
    private final List<Student> students = Arrays.asList(
            new Student(10),
            new Student(30),
            new Student(50),
            new Student(70),
            new Student(90),
            new Student(100)
    );

    @Test
    public void whenAClass() {
        List<Student> rsl = School.collect(students, x -> x.getScore() >= 70 && x.getScore() <= 100);
        List<Student> expected = Arrays.asList(new Student(70), new Student(90), new Student(100));
        assertThat(rsl.toString(), is(expected.toString()));
    }

    @Test
    public void whenBClass() {
        List<Student> rsl = School.collect(students, x -> x.getScore() >= 50 && x.getScore() < 70);
        List<Student> expected = Arrays.asList(new Student(50));
        assertThat(rsl.toString(), is(expected.toString()));
    }

    @Test
    public void whenCClass() {
        List<Student> rsl = School.collect(students, x -> x.getScore() >= 0 && x.getScore() < 50);
        List<Student> expected = Arrays.asList(new Student(10), new Student(30));
        assertThat(rsl.toString(), is(expected.toString()));
    }
}
