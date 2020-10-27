package com.company;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        int port  = 2048;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Cloud not listen on port: " + port + ".");
            System.exit(-1);
        }

        while (listening){
            new WorkerThread(serverSocket.accept()).start();
        }
        serverSocket.close();
    }
}
