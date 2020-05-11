package ru.job4j.concurrent;

/**
 * Parse the arguments for downloading the file
 */
public class Args {

    /**
     * File url
     */
    private String url;

    /**
     * File name
     */
    private String name;

    /**
     * Maximum download speed
     */
    private int maxSpeed;

    /**
     * Parse arguments
     * @param args - input arguments
     */
    public Args(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Url and maximum speed must be set");
        }

        if (!args[0].matches("^http[s]?://.*") || args[0].split("//")[1].lastIndexOf('/') == -1) {
            throw new IllegalArgumentException("Url is not valid: " + args[0]);
        }

        if (!args[1].matches("^\\d+$")) {
            throw new IllegalArgumentException("Maximum speed is incorrect: " + args[1]);
        }

        this.url = args[0];
        this.name = args[0].substring(args[0].lastIndexOf('/') + 1);
        this.maxSpeed = Integer.parseInt(args[1]);
    }

    public String getUrl() {
        return this.url;
    }

    public String getName() {
        return this.name;
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
    }
}
