import server.KKFunction;
import server.KKServerThread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class KKServer {

public void getConnection(){
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(5555);
            } catch (IOException e) {
                System.out.println("Could not listen on port: " + 5555 + ", " + e);
                System.exit(1);
            }
            Socket clientSocket = null;
            while (true){
                try {
                    clientSocket = serverSocket.accept();
                } catch (IOException e) {
                    System.out.println("Accept failed: " + 5555 + ", " + e);
                    System.exit(1);
                }
           Thread thread =new KKServerThread(clientSocket);
                thread.start();
            }}

    public static void main(String[] args) {
           KKServer kkServer = new KKServer();
           kkServer.getConnection();
    }
    }
