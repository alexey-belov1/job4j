package ru.job4j.io;

import java.io.File;
import java.util.*;

public class Search {

    public List<File> files(String parent) {
        return getFiles(parent, null, true);
    }

    public List<File> files(String parent, List<String> exts) {
        return getFiles(parent, exts, false);
    }

    private List<File> getFiles(String parent, List<String> exts, boolean isALL) {
        List<File> result = new ArrayList<>();

        Queue<File> data = new LinkedList<>();
        if (new File(parent).isDirectory()) {
            data.addAll(List.of(new File(parent).listFiles()));
        }

        while (!data.isEmpty()) {
            File file = data.poll();
            if (file.isDirectory()) {
                data.addAll(List.of(file.listFiles()));
            } else {
                if (isALL) {
                    result.add(file);
                } else {
                    String name = file.getName();
                    int index = name.lastIndexOf(".");
                    if (index != -1 && exts.contains(name.substring(index + 1))) {
                        result.add(file);
                    }
                }
            }
        }

        return result;
    }

}