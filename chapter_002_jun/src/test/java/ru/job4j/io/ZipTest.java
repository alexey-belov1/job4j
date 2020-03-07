package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ZipTest {
    private String path = System.getProperty("java.io.tmpdir");

    @Rule
    public TemporaryFolder folder = new TemporaryFolder(new File(path));

    @Test
    public void whenCreateZipAndThenCompareText() throws IOException {
        File grandParent = new File(folder.getRoot(), "grandParent");
        grandParent.mkdir();
        new File(grandParent, "grandParent.txt").createNewFile();
        new File(grandParent, "grandParent.doc").createNewFile();

        File parent = new File(grandParent, "parent");
        parent.mkdir();
        new File(parent, "parent.txt").createNewFile();
        new File(parent, "parent.doc").createNewFile();

        File child = new File(parent, "child");
        child.mkdir();
        File childTxt = new File(child, "child.txt");
        new File(child, "child.doc").createNewFile();

        String expected = "Test";
        try (PrintWriter out = new PrintWriter(childTxt)) {
            out.print(expected);
        }

        String directory = grandParent.getPath();
        String exclude = "*.doc";
        String output = folder.getRoot() + "/project.zip";

        String[] args = {"-d", directory, "-e", exclude, "-o", output};
        Zip zip = new Zip();
        zip.pack(new Args(args));

        String result = "";
        boolean hasFile = false;
        try (ZipInputStream read = new ZipInputStream(new BufferedInputStream(new FileInputStream(output)))) {
            ZipEntry entry = read.getNextEntry();
            while (entry != null) {
                if (entry.getName().endsWith(childTxt.getName())) {
                    byte[] buffer = read.readAllBytes();
                    result = new String(buffer, Charset.forName("windows-1251"));
                } else if (entry.getName().endsWith(exclude.substring(1))) {
                    hasFile = true;
                }
                entry = read.getNextEntry();
            }
        }

        assertThat(expected, is(result));
        assertThat(hasFile, is(false));
    }


}
