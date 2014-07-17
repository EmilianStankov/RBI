import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.hackbulgaria.corejava.RBIProtocol;

public class RBIServer {
    public static void main(String[] args) {

        int portNumber = RBIProtocol.PORT;

        long startTime;
        long endTime;
        long executionTime;

        try (ServerSocket serverSocket = new ServerSocket(portNumber); Socket clientSocket = serverSocket.accept();) {
            Runtime process = Runtime.getRuntime();
            String message;
            while (true) {
                startTime = System.currentTimeMillis();
                message = RBIProtocol.readMessage(clientSocket);
                System.out.println(message);
                // Process p = process.exec(message);
                // PrintWriter processOutput = new
                // PrintWriter(p.getOutputStream());
                // endTime = System.currentTimeMillis();
                // executionTime = endTime - startTime;
                RBIProtocol.writeMessage(clientSocket, String.format("{%s}", message));
                // System.out.println(executionTime);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber
                    + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
