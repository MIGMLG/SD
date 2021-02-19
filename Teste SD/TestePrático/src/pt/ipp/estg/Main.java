package pt.ipp.estg;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static SynchronizedUserDataList dataList = new SynchronizedUserDataList();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        int port  = 2048;
        boolean listening = true;

        UserData userData = new UserData(1000, "Arroz6969", "918177914");
        dataList.addUser(userData);

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Cloud not listen on port: " + port + ".");
            System.exit(-1);
        }

        /*MultiCastThread multiCastThreadServer = new MultiCastThread();
        multiCastThreadServer.start();*/

        while (listening){
            SocketThread wt = new SocketThread(serverSocket.accept());
            wt.start();
        }
        serverSocket.close();
    }
}
