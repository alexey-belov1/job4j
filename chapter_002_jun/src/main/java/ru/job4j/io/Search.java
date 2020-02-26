package ru.job4j.io;

import java.io.File;
import java.util.*;

public class Search {

    List<File> files(String parent, List<String> exts) {
        List<File> result = new ArrayList<>();
        File[] listfiles = new File(parent).listFiles();
        if (listfiles != null) {
            Arrays.stream(listfiles)
                .forEach(x -> {
                            if (x.isDirectory()) {
                                result.addAll(files(x.getPath(), exts));
                            } else {
                                String name = x.getName();
                                int index = name.lastIndexOf(".");
                                if (index != -1 && exts.contains(name.substring(index + 1))) {
                                    result.add(x);
                                }
                            }
                        }
                );
        }
        return result;
    }

}