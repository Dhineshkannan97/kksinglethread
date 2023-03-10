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
//

            System.out.println("before while");
            while ((inputLine = is.readLine()) != null) {
                System.out.println("inside while");
                outputLine = kkf.processInput(inputLine);
                os.println(outputLine);
//                getClass().getClassLoader().getResourceAsStream("punchline2.html").toString()
                File file = new File("C:\\Users\\Dhinesh Kannan\\Documents\\Streams\\kksinglethread5\\src\\main\\resources\\punchline2.html");
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                bw.write(outputLine);
                bw.close();
                os.flush();
                if (outputLine.equals("Bye"))
                    break;
            }

//
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
