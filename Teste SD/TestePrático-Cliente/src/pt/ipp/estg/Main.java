package pt.ipp.estg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main {

    private static Socket socket = null;
    private static ObjectInputStream in = null;
    private static ObjectOutputStream out = null;
    private static final long SLEEP_TIME = 30000;

    public static void main(String[] args) {
        try {
            socket = new Socket("127.0.0.1", 2048);
            /*MultiCastThread multiCastThreadClient = new MultiCastThread();
            multiCastThreadClient.start();*/
            while (true){
                sendData();
                Thread.sleep(SLEEP_TIME);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Don´t know about the host!");
            System.exit(1);
        }
    }

    public static void sendData() {
        if (socket != null) {
            try {
                out = new ObjectOutputStream(socket.getOutputStream());
                UserData userData = new UserData(1000, "Arroz6969", "918177914");
                out.writeObject(userData);

                in = new ObjectInputStream(socket.getInputStream());
                Key key = null;
                while ((key = (Key) in.readObject()) != null) {
                    out.writeObject(key);
                    break;
                }

                String response = null;
                while ((response = (String) in.readObject()) != null) {
                    System.out.println(response);
                    break;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Sessão não iniciada!");
        }
    }
}
