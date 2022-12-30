package client;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class KKClient {
    public  void socClient(){
        try {
            Socket kkSocket = new Socket("localhost", 5555);
            PrintStream os = new PrintStream(kkSocket.getOutputStream());
            DataInputStream is = new DataInputStream(kkSocket.getInputStream());
            StringBuffer buf = new StringBuffer(50);

            int c;
            String fromServer;

            while ((fromServer = is.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye"))
                    break;
                while ((c = System.in.read()) != '\n') {
                    buf.append((char) c);
                }
                System.out.println("Client: " + buf);
                os.println(buf.toString());
                os.flush();
                buf.setLength(0);
            }
            os.close();
            is.close();
            kkSocket.close();
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (Exception e) {
            System.err.println("Exception:  "+e);
        }
    }

    public static void main(String[] args) {
        KKClient client =new KKClient();
        client.socClient();
    }
    }

