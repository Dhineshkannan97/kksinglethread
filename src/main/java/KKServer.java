import server.KKServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class KKServer {

    public void getConnection() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8060);
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + 8060 + ", " + e);
            System.exit(1);
        }

        Socket clientSocket = null;
        while (true) {
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("Accept failed: " + 8060 + ", " + e);
                System.exit(1);
            }
//            String httpResponse = "HTTP/1.1 200 OK\r\n\r\n";
//            clientSocket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
            Thread thread = new KKServerThread(clientSocket);
            thread.start();
        }
    }

    public static void main(String[] args) throws IOException {
        KKServer kkServer = new KKServer();
        kkServer.getConnection();
    }
}
