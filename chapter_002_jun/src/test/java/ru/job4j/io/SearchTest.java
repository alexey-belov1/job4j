package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SearchTest {
    private String path = System.getProperty("java.io.tmpdir");

    @Rule
    public TemporaryFolder folder = new TemporaryFolder(new File(path));

    @Test
    public void whenSearchFiles() throws IOException {
        File grandParent = new File(folder.getRoot(), "grandParent");
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
        new File(child, "child.png").createNewFile();

        List<File> listEmpty = new Search().files(grandParent.getPath(), Collections.emptyList());
        List<File> listTxt = new Search().files(grandParent.getPath(), List.of("txt"));
        List<File> listTxtDoc = new Search().files(grandParent.getPath(), List.of("txt", "doc"));
        List<File> listTxtDocXML = new Search().files(grandParent.getPath(), List.of("txt", "doc", "xml"));
        List<File> listAll = new Search().files(grandParent.getPath());
        List<File> listNameParent = new Search().files(grandParent.getPath(), "parent");
        List<File> listNameChild = new Search().files(grandParent.getPath(), "child");

        assertThat(listEmpty.size(), is(0));
        assertThat(listTxt.size(), is(4));
        assertThat(listTxtDoc.size(), is(8));
        assertThat(listTxtDocXML.size(), is(12));
        assertThat(listAll.size(), is(13));
        assertThat(listNameParent.size(), is(9));
        assertThat(listNameChild.size(), is(4));
    }


}
