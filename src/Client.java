import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args){
        clientSetup();
    }
    public static void clientSetup(){
        try {
            Scanner scanner = new Scanner(System.in);
            Socket clientSocket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            System.out.println("Connected to the server.");

            System.out.println("if you want to enter the chat room please enter a Username:\notherwise enter \"quit\"");
            String usernameToSend = scanner.next();
            out.println(usernameToSend);

            // terminal prints
            String resultFromServer = in.readLine();
            String messageFromServer;

            System.out.println("Received result from server: " + resultFromServer);

            //send message to server and print in terminal
            String messageToSend;
            while (true) {
                messageToSend = scanner.next();
                out.println(messageToSend);
                messageFromServer = in.readLine();
                System.out.println(messageFromServer);
                if (messageToSend.equals("quit")) {
                    break;
                }
            }

            // Close streams and socket
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
