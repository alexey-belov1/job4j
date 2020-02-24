package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
                PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            List<String> initialDate = new ArrayList<>(1);
            read.lines()
                    .filter(x -> !x.isBlank())
                    .forEach(x -> {
                        int type = Integer.parseInt(x.substring(0, x.indexOf(" ")));
                        String date = x.substring(x.indexOf(" ") + 1);
                        if (type >= 400 && initialDate.isEmpty()) {
                            initialDate.add(date);
                        } else if (type < 400 && !initialDate.isEmpty()) {
                            out.println(initialDate.get(0) + " " + date);
                            initialDate.remove(0);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}