package ru.job4j.tdd;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GeneratorTest {

    @Ignore
    @Test
    public void produce() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>() { {
            put("name", "Petr Arsentev");
            put("subject", "you");
        } };
        String result = new SimpleGenerator().produce(template, args);
        String expected = "I am a Petr Arsentev, Who are you?";
        assertThat(expected, is(result));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void produceWithLackKeys() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>() { {
            put("subject", "you");
        } };
        new SimpleGenerator().produce(template, args);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void produceWithOverageKeys() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>() { {
            put("name", "Petr Arsentev");
            put("project", "job4j");
            put("subject", "you");
        } };
        new SimpleGenerator().produce(template, args);
    }
}