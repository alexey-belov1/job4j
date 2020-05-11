package ru.job4j.concurrent;

import java.io.*;
import java.net.URL;

/**
 * Download file from internet
 */
public class FileDownload {

    /**
     * Buffer size for downloading a file [byte]
     */
    private int sizeBuffer = 1024;

    /**
     *  Download file to the current directory
     * @param url - file url
     * @param name - file name
     * @param maxSpeed - maximum download speed [kb]
     */
    public void download(String url, String name, int maxSpeed) {

        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(name)) {

            System.out.println("Start download.");

            byte[] dataBuffer = new byte[sizeBuffer];
            int bytesRead;
            double sizeFile = 0;
            double part = 0;
            long tBegin = System.currentTimeMillis();

            print(sizeFile, 0);

            while ((bytesRead = in.read(dataBuffer, 0, sizeBuffer)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                part += (double) bytesRead / 1024.0;

                if (System.currentTimeMillis() - tBegin >= 1000) {
                    long pause = (long) ((part > maxSpeed) ? 1000 * (part / maxSpeed - 1) : 0);
                    Thread.sleep(pause);
                    sizeFile += part;
                    print(sizeFile, 1000 * part / (System.currentTimeMillis() - tBegin));
                    part = 0;
                    tBegin = System.currentTimeMillis();
                }
            }

            print(sizeFile + part, 0);
            System.out.println("\nDownload is finished.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void print(double sizeFile, double speed) {
        System.out.print(String.format("\rLoad: %.1f kb; ", sizeFile));
        System.out.print(String.format("speed: %.1f kb/s", speed));
    }

    /**
     * Start download
     * @param args - https://download.jetbrains.com/idea/ideaIE-2020.1.exe 300
     */
    public static void main(String[] args) {
        Args in = new Args(args);
        new FileDownload().download(in.getUrl(), in.getName(), in.getMaxSpeed());
    }
}