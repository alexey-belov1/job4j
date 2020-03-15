package ru.job4j.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FindTest {
    private final String path = System.getProperty("java.io.tmpdir");
    private File grandParent;
    private String directory, output;

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Rule
    public TemporaryFolder folder = new TemporaryFolder(new File(path));

    @Before
    public void init() throws IOException {
        grandParent = new File(folder.getRoot(), "grandParent");
        grandParent.mkdir();
        new File(grandParent, "grandParent.txt").createNewFile();
        new File(grandParent, "grandParent.doc").createNewFile();
        new File(grandParent, "grandParent.xml").createNewFile();

        File parent1 = new File(grandParent, "parent1");
        parent1.mkdir();
        new File(parent1, "parent1.txt").createNewFile();
        new File(parent1, "parent1.doc").createNewFile();
        new File(parent1, "parent1.xml").createNewFile();

        File parent2 = new File(grandParent, "parent2");
        parent2.mkdir();
        new File(parent2, "parent2.txt").createNewFile();
        new File(parent2, "parent2.doc").createNewFile();
        new File(parent2, "parent2.xml").createNewFile();

        File child = new File(parent1, "child");
        child.mkdir();
        new File(child, "child.txt").createNewFile();
        new File(child, "child.doc").createNewFile();
        new File(child, "child.xml").createNewFile();

        directory = grandParent.getPath();
        output = folder.getRoot() + "/result.txt";

        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(System.out);
    }

    @Test
    public void whenSearchFullMatch() {
        String[] args = {"-d", directory, "-n", "child.doc", "-f", "-o", output};
        new Find().find(new Args(args));
        List<String> res = null;
        try {
            res = Files.readAllLines(Paths.get(output));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(res.size(), is(1));
    }

    @Test
    public void whenSearchRegEx() {
        String[] args = {"-d", directory, "-n", "^child.*", "-r", "-o", output};
        new Find().find(new Args(args));
        List<String> res = null;
        try {
            res = Files.readAllLines(Paths.get(output));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(res.size(), is(3));
    }

    @Test
    public void whenSearchMask() {
        String[] args = {"-d", directory, "-n", "*.doc", "-m", "-o", output};
        new Find().find(new Args(args));
        List<String> res = null;
        try {
            res = Files.readAllLines(Paths.get(output));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(res.size(), is(4));
    }

    @Test
    public void whenAbsentD() {
        String[] args = {""};
        new Find().find(new Args(args));
        assertThat(this.out.toString(), is("Необходимо указать директорию для поиска -d" + System.lineSeparator()));
    }

    @Test
    public void whenNotAD() {
        String[] args = {"-d", "123"};
        new Find().find(new Args(args));
        assertThat(this.out.toString(), is("Указанный путь не является директорией -d" + System.lineSeparator()));
    }

    @Test
    public void whenAbsentN() {
        String[] args = {"-d", directory};
        new Find().find(new Args(args));
        assertThat(this.out.toString(), is("Необходимо указать имя файла -n" + System.lineSeparator()));
    }

    @Test
    public void whenAbsentFRM() {
        String[] args = {"-d", directory, "-n", "child.doc"};
        new Find().find(new Args(args));
        assertThat(this.out.toString(), is("Необходимо выбрать один из вариантов поиска -f, -r, -m" + System.lineSeparator()));
    }

    @Test
    public void whenHasAllFRM() {
        String[] args = {"-d", directory, "-n", "child.doc", "-f", "-r", "-m"};
        new Find().find(new Args(args));
        assertThat(this.out.toString(), is("Необходимо выбрать один из вариантов поиска -f, -r, -m" + System.lineSeparator()));
    }

    @Test
    public void whenAbsentO() {
        String[] args = {"-d", directory, "-n", "child.doc", "-f"};
        new Find().find(new Args(args));
        assertThat(this.out.toString(), is("Необходимо указать файл для вывода результата -o" + System.lineSeparator()));
    }
}
