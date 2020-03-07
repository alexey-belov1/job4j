package ru.job4j.io;

public class Args {
    private String directory;
    private String exclude;
    private String output;

    public Args(String[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-d")) {
                this.directory = args[++i];
            } else if (args[i].equals("-e")) {
                this.exclude = args[i + 1].substring(args[i + 1].indexOf("*.") + 2);
                i++;
            } else if (args[i].equals("-o")) {
                this.output = args[++i];
            }
        }
    }

    public String directory() {
        return this.directory;
    }

    public String exclude() {
        return this.exclude;
    }

    public String output() {
        return this.output;
    }
}
