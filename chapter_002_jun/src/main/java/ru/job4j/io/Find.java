package ru.job4j.io;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Find {

    public void find(Args args) {
        if (validate(args)) {
            List<File> files = null;
            if (args.isFullMatch()) {
                files = new Search().files(args.directory(), new NameFilter(args.name()));
            } else if (args.isRegex()) {
                files = new Search().files(args.directory(), new RegExFilter(args.name()));
            } else if (args.isMask()) {
                files = new Search().files(args.directory(), new MaskFilter(args.name()));
            }

            try {
                List<String> log = files.stream().map(File::getPath).collect(Collectors.toList());
                Files.write(Paths.get(args.output()), log);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
                    || name.toLowerCase().equals(this.name.toLowerCase());
        }
    }

    private class RegExFilter implements FilenameFilter {
        private String regex;

        public RegExFilter(String regex) {
            this.regex = regex;
        }

        @Override
        public boolean accept(File dir, String name) {
            return new File(dir + "/" + name).isDirectory()
                    || Pattern.compile(regex).matcher(name).find();
        }
    }

    private class MaskFilter implements FilenameFilter {
        private String mask;

        public MaskFilter(String mask) {
            this.mask = mask;
        }

        @Override
        public boolean accept(File dir, String name) {
            return new File(dir + "/" + name).isDirectory()
                    || Pattern.compile(maskToReg(mask)).matcher(name).find();
        }
    }

    private String maskToReg(String mask) {
        String regex = mask;
        String[] symbols = {"\\", "#", "|", "(", ")", "[", "]", "{", "}", "^", "$", "+", "."};
        for (String s : symbols) {
            regex = regex.replace(s, "\\" + s);
        }

        regex = regex.replace("*", ".*");
        regex = regex.replace("?", ".");

        return "^" + regex + "$";
    }

    private boolean validate(Args args) {
        boolean result = false;
        if (args.directory() != null) {
            if (new File(args.directory()).isDirectory()) {
                if (args.name() != null) {
                    if ((args.isFullMatch() && !args.isRegex() && !args.isMask())
                            || (!args.isFullMatch() && args.isRegex() && !args.isMask())
                            || (!args.isFullMatch() && !args.isRegex() && args.isMask())) {
                        if (args.output() != null) {
                            result = true;
                        } else {
                            System.out.println("Необходимо указать файл для вывода результата -o");
                        }
                    } else {
                        System.out.println("Необходимо выбрать один из вариантов поиска -f, -r, -m");
                    }
                } else {
                    System.out.println("Необходимо указать имя файла -n");
                }
            } else {
                System.out.println("Указанный путь не является директорией -d");
            }
        } else {
            System.out.println("Необходимо указать директорию для поиска -d");
        }
        return result;
    }
}