import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {
    private static final int PORT = 1234;
    private static List<ConnectionToClients> clientsList = new ArrayList<>();

    public static void main(String[] args){
        serverSetup();
    }
    public static void serverSetup(){
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Chat Server is running on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ConnectionToClients client = new ConnectionToClients(clientSocket);
                clientsList.add(client);
                Thread clientThread = new Thread(client);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // this class handle connection from server to client
    private static class ConnectionToClients extends Thread {
        private Socket clientSocket;
        BufferedReader serverIn;
        PrintWriter serverOut;


        public ConnectionToClients(Socket socket){
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                serverOut = new PrintWriter(clientSocket.getOutputStream(), true);

                String receivedUsername = serverIn.readLine();
                if (receivedUsername.equals("quit")) {
                    serverOut.println("quit");
                } else {
                    //send message to client
                    serverOut.println("you are entered the chat room");
                    System.out.println(receivedUsername + " entered the chat room");

                    //receive all messages from anybody
                    String receivedMessage;
                    while ((receivedMessage = serverIn.readLine()) != null) {
                        if (receivedMessage.equals("quit")) {
                            break;
                        }
                        //print message to terminal
                        System.out.println(receivedUsername + ": " + receivedMessage);

                        //send message to every client in list
                        for (ConnectionToClients client : clientsList) {
                            client.sendMessage(receivedUsername + ": " + receivedMessage);
                        }
                    }

                    // exit some random user name
                    System.out.println(receivedUsername + " exited the chatroom");
                }

                // Close streams and socket
                serverIn.close();
                serverOut.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void sendMessage(String message) {
            serverOut.println(message);
        }
    }

}
