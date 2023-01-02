package server;

import java.io.*;
import java.net.Socket;

public class KKServerThread extends Thread {
    Socket clientSocket;

    public KKServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        super.run();
        try {
            DataInputStream is = new DataInputStream(
                    new BufferedInputStream(clientSocket.getInputStream()));
            PrintStream os = new PrintStream(
                    new BufferedOutputStream(clientSocket.getOutputStream(), 1024), false);
            KKFunction kkf = new KKFunction();
            String inputLine, outputLine;

            outputLine = kkf.processInput(null);
            os.println(outputLine);
            os.flush();
            while ((inputLine = is.readLine()) != null) {
                outputLine = kkf.processInput(inputLine);
                File file = new File("C:\\Users\\Dhinesh Kannan\\IdeaProjects\\kksinglethread\\src\\main\\resources\\punchline1.html");
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                bw.write(outputLine);
                bw.close();
                os.println(outputLine);
                os.flush();
                if (outputLine.equals("Bye"))
                    break;
            }
//            os.close();
//            is.close();
//            clientSocket.close();
//            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
