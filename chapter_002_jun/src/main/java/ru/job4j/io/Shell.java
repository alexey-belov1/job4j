package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Shell {

    private Stack<String> stack = new Stack<>();

    public Shell() {
        Arrays.stream(new File("").getAbsolutePath().split("[\\\\,/]")).forEach(x -> stack.push(x));
    }

    public Shell(String str) throws IOException {
        if (!new File(str).isDirectory()) {
            throw new NotDirectoryException(String.format("Такой директории не существует %s", str));
        }
        Arrays.stream(new File(str + "\\").getAbsolutePath().split("[\\\\,/]")).forEach(x -> stack.push(x));
    }

    private Shell(Stack<String> stack) {
        this.stack = stack;
    }

    public Shell cd(final String path) {
        if (path.equals("..")) {
            if (stack.size() > 1) {
                stack.pop();
            }
        } else if (path.equals("/")) {
            while (stack.size() > 1) {
                stack.pop();
            }
        } else {
            Stack<String> log = new Stack<>();
            String tempPath = path;

            while (tempPath.startsWith("../")) {
                if (stack.size() > 1) {
                    log.push(stack.pop());
                }
                tempPath = tempPath.substring(3);
            }

            while (tempPath.startsWith("./")) {
                tempPath = tempPath.substring(2);
            }

            if (tempPath.length() > 0) {
                String[] folders = tempPath.split("/");
                for (int i = 0; i < folders.length; i++) {
                    if (List.of(new File(path()).list()).contains(folders[i])) {
                        stack.push(folders[i]);
                    } else {
                        for (int j = 0; j < i; j++) {
                            stack.pop();
                        }
                        while (!log.empty()) {
                            stack.push(log.pop());
                        }
                        System.out.println("Не удалось найти указанный путь");
                        break;
                    }
                }
            }
        }

        return new Shell(stack);
    }

    public String path() {
        StringBuilder path = new StringBuilder();
        stack.forEach(x -> path.append(x + "/"));
        return path.substring(0, path.length() - 1);
    }
}