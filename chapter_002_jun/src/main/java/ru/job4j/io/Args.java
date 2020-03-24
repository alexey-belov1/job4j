package ru.job4j.io;

import java.util.function.Predicate;

public class Args {
    private String directory;
    private String exclude;
    private String name;
    private boolean fullMatch;
    private boolean mask;
    private boolean regex;
    private String output;

    public Args(String[] args) {
        Predicate<Integer> existValue = i -> i < args.length && !args[i].startsWith("-");
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-d": this.directory = (existValue.test(++i)) ? args[i] : null;
                           break;
                case "-e": this.exclude = (existValue.test(++i) && args[i].contains("*.")) ? args[i].substring(args[i].indexOf("*.") + 2) : null;
                           break;
                case "-n": this.name = (existValue.test(++i)) ? args[i] : null;
                           break;
                case "-f": this.fullMatch = true;
                           break;
                case "-m": this.mask = true;
                           break;
                case "-r": this.regex = true;
                           break;
                case "-o": this.output = (existValue.test(++i)) ? args[i] : null;
                           break;
                default:   break;
            }
        }
    }

    public String directory() {
        return this.directory;
    }

    public String exclude() {
        return this.exclude;
    }

    public String name() {
        return this.name;
    }

    public boolean isFullMatch() {
        return this.fullMatch;
    }

    public boolean isMask() {
        return this.mask;
    }

    public boolean isRegex() {
        return this.regex;
    }

    public String output() {
        return this.output;
    }
}
