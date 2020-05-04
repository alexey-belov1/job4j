package ru.job4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class Store {
    private Cash<String, String> cash = new Cash<>();
    private String directory;

    public Store(String directory) {
        this.directory = directory;
    }

    public String get(String name) throws NoSuchFileException {
        String path = this.directory + "/" + name;

        if (!Files.exists(Paths.get(path))) {
            throw new NoSuchFileException(path);
        }

        if (this.cash.get(name) == null) {
            try {
                this.cash.put(name, Files.readString(Paths.get(path)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.cash.get(name);
    }
}
