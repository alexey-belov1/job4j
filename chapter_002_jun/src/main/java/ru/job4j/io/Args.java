package ru.job4j.io;

public class Args {
    private String directory;
    private String exclude;
    private String name;
    private boolean fullMatch;
    private boolean mask;
    private boolean regex;
    private String output;

    public Args(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-d": this.directory = args[++i];
                           break;
                case "-e": this.exclude = args[i + 1].substring(args[i + 1].indexOf("*.") + 2);
                           i++;
                           break;
                case "-n": this.name = args[++i];
                           break;
                case "-f": this.fullMatch = true;
                           break;
                case "-m": this.mask = true;
                           break;
                case "-r": this.regex = true;
                           break;
                case "-o": this.output = args[++i];
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
