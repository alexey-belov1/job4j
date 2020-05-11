package ru.job4j.concurrent;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ArgsTest {

    @Test
    public void whenAllArgumentsEntered() {
        String[] arg = new String[] {"https://download.jetbrains.com/idea/ideaIE-2020.1.exe", "1000"};
        Args args = new Args(arg);
        assertThat(args.getUrl(), is("https://download.jetbrains.com/idea/ideaIE-2020.1.exe"));
        assertThat(args.getName(), is("ideaIE-2020.1.exe"));
        assertThat(args.getMaxSpeed(), is(1000));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenOnlyOneArgumentEntered() {
        String[] arg = new String[] {"https://download.jetbrains.com/idea/ideaIE-2020.1.exe"};
        new Args(arg);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenWrongUrl1() {
        String[] arg = new String[] {"https://download.jetbrains.com", "1000"};
        new Args(arg);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenWrongSpeed() {
        String[] arg = new String[] {"https://download.jetbrains.com/idea/ideaIE-2020.1.exe", "1000s"};
        new Args(arg);
    }
}
