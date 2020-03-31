package ru.job4j.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.NotDirectoryException;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ShellTest {
    private final String path = System.getProperty("java.io.tmpdir");
    private File grandParent;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Rule
    public TemporaryFolder folder = new TemporaryFolder(new File(path));

    @Before
    public void init() {
        grandParent = new File(folder.getRoot(), "grandParent");
        grandParent.mkdir();

        File parent = new File(grandParent, "parent");
        parent.mkdir();

        File child = new File(parent, "child");
        child.mkdir();

        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(System.out);
    }

    private String replace(String str) {
        return str.replace('\\', '/');
    }

    @Test
    public void whenGetRoot() throws IOException {
        Shell shell = new Shell(grandParent.getPath());
        shell = shell.cd("/");
        assertThat(List.of("C:", "").contains(replace(shell.cd("/").path())), is(true));
    }

    @Test
    public void whenUsePointSlash() throws IOException {
        Shell shell = new Shell(grandParent.getPath());
        shell = shell.cd("./");
        assertThat(shell.path(), is(replace(grandParent.getPath())));
    }

    @Test
    public void whenToChild() throws IOException {
        Shell shell = new Shell(grandParent.getPath());
        shell = shell.cd("parent").cd("child");
        assertThat(shell.path(), is(replace(grandParent.getPath()) + "/parent/child"));
    }

    @Test
    public void whenToChildInOneDirectory() throws IOException {
        Shell shell = new Shell(grandParent.getPath());
        shell = shell.cd("parent/child");
        assertThat(shell.path(), is(replace(grandParent.getPath()) + "/parent/child"));
    }

    @Test
    public void whenToChildAndBack() throws IOException {
        Shell shell = new Shell(grandParent.getPath());
        shell = shell.cd("parent").cd("child").cd("..");
        assertThat(shell.path(), is(replace(grandParent.getPath()) + "/parent"));
    }

    @Test
    public void whenToChildAndBackAndUseSlash() throws IOException {
        Shell shell = new Shell(grandParent.getPath());
        shell = shell.cd("parent").cd("child").cd("../");
        assertThat(shell.path(), is(replace(grandParent.getPath()) + "/parent"));
    }

    @Test
    public void whenToChildAndTwiceBack() throws IOException {
        Shell shell = new Shell(grandParent.getPath());
        shell = shell.cd("parent").cd("child").cd("../../");
        assertThat(shell.path(), is(replace(grandParent.getPath())));
    }

    @Test
    public void whenToChildAndTwiceBackAndToParent() throws IOException {
        Shell shell = new Shell(grandParent.getPath());
        shell = shell.cd("parent").cd("child").cd("../../parent");
        assertThat(shell.path(), is(replace(grandParent.getPath()) + "/parent"));
    }

    @Test
    public void whenNotExistDirectory() throws IOException {
        Shell shell = new Shell(grandParent.getPath());
        shell = shell.cd("err");
        assertThat(shell.path(), is(replace(grandParent.getPath())));
        assertThat(this.out.toString(), is("Не удалось найти указанный путь" + System.lineSeparator()));
    }

    @Test
    public void whenToChildAndTwiceBackAndNotExitDirectory() throws IOException {
        Shell shell = new Shell(grandParent.getPath());
        shell = shell.cd("parent").cd("child").cd("../../err");
        assertThat(shell.path(), is(replace(grandParent.getPath()) + "/parent/child"));
        assertThat(this.out.toString(), is("Не удалось найти указанный путь" + System.lineSeparator()));
    }

    @Test
    public void whenEmptyShellGetRoot() throws IOException {
        Shell shell = new Shell();
        assertThat(List.of("C:", "").contains(shell.cd("/").path()), is(true));
    }

    @Test(expected = NotDirectoryException.class)
    public void whenWrongPathInShell() throws IOException {
        Shell shell = new Shell("err");
    }

    @Test
    public void whenGetRootAndThenGetParent() throws IOException {
        Shell shell = new Shell(grandParent.getPath());
        shell = shell.cd("/").cd("../../");
        assertThat(List.of("C:", "").contains(shell.cd("/").path()), is(true));
    }
}
