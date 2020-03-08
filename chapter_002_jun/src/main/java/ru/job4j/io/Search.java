package ru.job4j.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.*;

public class Search {

    public List<File> files(String parent) {
        return getFiles(parent, null);
    }

    public List<File> files(String parent, String name) {
        return getFiles(parent, new NameFilter(name));
    }

    public List<File> files(String parent, List<String> exts) {
        return getFiles(parent, new ExtsFilter(exts));
    }

    public List<File> files(String parent, FilenameFilter filter) {
        return getFiles(parent, filter);
    }

    private List<File> getFiles(String parent, FilenameFilter filter) {
        List<File> result = new ArrayList<>();

        Queue<File> data = new LinkedList<>();
        if (new File(parent).isDirectory()) {
            data.add(new File(parent));
            while (!data.isEmpty()) {
                File file = data.poll();
                if (file.isDirectory()) {
                    data.addAll(List.of(file.listFiles(filter)));
                } else {
                    result.add(file);
                }
            }
        }
        return result;
    }

    private class ExtsFilter implements FilenameFilter {
        private List<String> exts;

        public ExtsFilter(List<String> exts) {
            this.exts = exts;
        }

        @Override
        public boolean accept(File dir, String name) {
            return new File(dir + "/" + name).isDirectory()
                    || exts.stream().anyMatch(x -> name.toLowerCase().endsWith(x.toLowerCase()));
        }
    }

    private class NameFilter implements FilenameFilter {
        private String name;

        public NameFilter(String name) {
            this.name = name;
        }

        @Override
        public boolean accept(File dir, String name) {
            return new File(dir + "/" + name).isDirectory()
                    || name.toLowerCase().contains(this.name.toLowerCase());
        }
    }
}