import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.hackbulgaria.corejava.RBIProtocol;

public class RBIServer {
    public static void main(String[] args) {

        int portNumber = RBIProtocol.PORT;

        long startTime;
        long endTime;
        long executionTime;
        try (ServerSocket serverSocket = new ServerSocket(portNumber); Socket clientSocket = serverSocket.accept();) {
            Runtime runtime = Runtime.getRuntime();
            String message;
            while (true) {
                try {
                    message = RBIProtocol.readMessage(clientSocket);
                    startTime = System.currentTimeMillis();
                    System.out.println(message);
                    Process p = runtime.exec(message);
                    p.waitFor();
                    InputStream processOutput = p.getInputStream();
                    Scanner scanner = new Scanner(processOutput);
                    String line = "";
                    StringBuilder builder = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        line = scanner.nextLine();
                        builder.append(line);
                    }
                    System.out.println(builder);
                    endTime = System.currentTimeMillis();
                    executionTime = endTime - startTime;
                    System.out.println(String.format("Command executed in {%d} milliseconds!", executionTime));
                    RBIProtocol.writeMessage(clientSocket, String.format(
                            "{%s} executed in {%d} milliseconds, with result: {%s}", message, executionTime, builder));
                } catch (IOException e) {
                    RBIProtocol.writeMessage(clientSocket, "Invalid command!");
                }
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber
                    + " or listening for a connection");
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
