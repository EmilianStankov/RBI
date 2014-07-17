package com.hackbulgaria.corejava;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class RBIProtocol {

    public static final String EOM = "<[?!EOM!?]>\n";
    public static final int PORT = 1234;

    public static String readMessage (Socket socket) {      
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder message = new StringBuilder();
            String line;
            while(!((line = in.readLine()).equals(EOM))) {
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
            out.print(message);
            out.print("\n");
            out.print(EOM);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}
