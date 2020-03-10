package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServer {

    private String getMsg(String text) {
        String result = "";
        Pattern pattern = Pattern.compile("\\?msg=(\\S*)");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            result = matcher.group().split("=")[1];
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {

                    in.mark(1);
                    String str = in.readLine();
                    while (!str.isEmpty()) {
                        System.out.println(str);
                        str = in.readLine();
                    }

                    in.reset();
                    if (new EchoServer().getMsg(in.readLine()).equals("Bye")) {
                        out.write("Server off".getBytes());
                        break;
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                }
            }
        }
    }
}