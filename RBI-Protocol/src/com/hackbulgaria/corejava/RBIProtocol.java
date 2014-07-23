package com.hackbulgaria.corejava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Path;

public class RBIProtocol {

    public static final String EOM = "<[?!EOM!?]>";
    public static final String FILE = "<[?!FILE!?]>";
    public static final int PORT = 1234;

    public static String readMessage(Socket socket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder message = new StringBuilder();
            String line;
            while (!((line = in.readLine()).contains(EOM))) {
                message.append(line);
            }
            return message.toString();
        } catch (IOException e) {
            System.out.println("Error!");
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void writeMessage(Socket socket, String message) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message);
            out.println(EOM);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    
    public static byte[] readFile(Socket socket) {
        
        return null;
    }
    
    public static void writeFile(Socket socket, Path path) {
        
    }
}
