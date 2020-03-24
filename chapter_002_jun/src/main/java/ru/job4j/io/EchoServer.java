package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {

                    String msg = in.readLine();
                    int index = msg.indexOf("?msg=");
                    msg = (index != -1) ? msg.substring(index + 5).split(" ")[0] : "";

                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (msg.equals("Exit")) {
                        server.close();
                    } else if (msg.equals("Hello")) {
                        out.write("Hello\r\n".getBytes());
                    } else {
                        out.write((msg + "\r\n").getBytes());
                    }
                }
            }
        }
    }
}