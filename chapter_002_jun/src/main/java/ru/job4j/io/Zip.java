package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void pack(Args args) {

        List<File> sources = seekBy(args.directory(), new ExtFilter(args.exclude()));
        if (sources != null) {
            try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(args.output())))) {
                for (File file : sources) {
                    zip.putNextEntry(new ZipEntry(getPathToZip(args.directory(), file)));
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                        zip.write(out.readAllBytes());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<File> seekBy(String directory, FilenameFilter filter) {
        List<File> result = new ArrayList<>();

        Queue<File> data = new LinkedList<>();
        if (new File(directory).isDirectory()) {
            data.add(new File(directory));
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

    private String getPathToZip(String root, File file) {
        String pathToZip = "";
        try {
            int index = new File(root).getCanonicalPath().length() + 1;
            pathToZip = file.getCanonicalPath().substring(index);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return pathToZip;
    }


    private class ExtFilter implements FilenameFilter {
        private String ext;

        public ExtFilter(String ext) {
            this.ext = ext;
        }

        @Override
        public boolean accept(File dir, String name) {
            return new File(dir + "/" + name).isDirectory()
                    || !name.toLowerCase().endsWith(ext.toLowerCase());
        }
    }
}