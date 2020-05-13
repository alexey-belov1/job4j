package ru.job4j.synch;

import java.io.*;

public class ParseFile {

    private File file;

    public synchronized void setFile(File f) {
        this.file = f;
    }

    public synchronized File getFile() {
        return this.file;
    }

    public synchronized String getContent() throws IOException {
        String output;
        try (BufferedInputStream i = new BufferedInputStream(new FileInputStream(this.file))) {
            output = new String(i.readAllBytes());
        }
        return output;
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        String output = "";
        try (BufferedInputStream i = new BufferedInputStream(new FileInputStream(this.file))) {
            int data;
            while ((data = i.read()) > 0) {
                if (data < 0x80) {
                    output += (char) data;
                }
            }
        }
        return output;
    }

    public synchronized void saveContent(String content) throws IOException {
        try (PrintWriter o = new PrintWriter(new BufferedOutputStream(new FileOutputStream(this.file)))) {
            o.write(content);
        }
    }
}