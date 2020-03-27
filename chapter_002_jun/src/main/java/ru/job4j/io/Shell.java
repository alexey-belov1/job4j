package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.nio.file.Paths;

public class Shell {

    private final String currentPath;

    public Shell() {
        this.currentPath = new File("").getAbsolutePath();
    }

    public Shell(String str) throws IOException {
        if (new File(str).isDirectory()) {
            this.currentPath = new File(str).getAbsolutePath();
        } else {
            throw new NotDirectoryException(String.format("Такой директории не существует %s", str));
        }
    }

    public Shell cd(final String path) throws IOException {
        String result;

        switch (path) {
            case (".."):
                result = (new File(currentPath).getParent() != null) ? new File(currentPath).getParent() : currentPath;
                break;
            case ("/"):
                result = Paths.get(currentPath).getRoot().toString();
                break;
            default:
                result = new File(currentPath + "/" + path).getCanonicalPath();
                break;
        }

        if (new File(result).isDirectory()) {
            return new Shell(result);
        } else {
            System.out.println("Не удалось найти указанный путь");
            return this;
        }
    }

    public String path() {
        return this.currentPath;
    }
}