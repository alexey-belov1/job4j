package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class ArgsTest {
    @Test
    public void whenAllParametersEntered() {
        String[] arg = {"-d", "dir", "-e", "*.exc", "-n", "name", "-f", "-m", "-r", "-o", "out"};
        Args args = new Args(arg);
        assertThat(args.directory(), is("dir"));
        assertThat(args.exclude(), is("exc"));
        assertThat(args.name(), is("name"));
        assertThat(args.isFullMatch(), is(true));
        assertThat(args.isMask(), is(true));
        assertThat(args.isRegex(), is(true));
        assertThat(args.output(), is("out"));
    }

    @Test
    public void whenNotAllParametersEntered() {
        String[] arg = {"-d", "-e", "-n", "-o"};
        Args args = new Args(arg);
        assertThat(args.directory(), is(nullValue()));
        assertThat(args.exclude(), is(nullValue()));
        assertThat(args.name(), is(nullValue()));
        assertThat(args.isFullMatch(), is(false));
        assertThat(args.isMask(), is(false));
        assertThat(args.isRegex(), is(false));
        assertThat(args.output(), is(nullValue()));
    }
}
