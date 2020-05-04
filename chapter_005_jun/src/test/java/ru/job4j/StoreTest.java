package ru.job4j;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StoreTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenGetFileContent() throws NoSuchFileException {
        try {
            Files.writeString(folder.newFile("Name.txt").toPath(), "hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Store store = new Store(folder.getRoot().toString());
        assertThat(store.get("Name.txt"), is("hello"));
    }

    @Test (expected = NoSuchFileException.class)
    public void whenFileDoesNotExist() throws NoSuchFileException {
        Store store = new Store(folder.getRoot().toString());
        store.get("Name.txt");
    }
}
