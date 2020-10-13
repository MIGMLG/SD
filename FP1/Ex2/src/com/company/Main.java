package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        ServerSocket serverSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            serverSocket = new ServerSocket(7);

        } catch (IOException e) {
            System.out.println("Could not listen on port 7!");
            System.exit(1);
        }

        Socket clientSocket = null;

        try {
            clientSocket = serverSocket.accept();
            while (clientSocket.isConnected()) {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String message = in.readLine();
                System.out.println("Mensagem do Cliente: " + message);
                out.println("" + serverSocket.getLocalSocketAddress().toString() + ":" + clientSocket.getLocalAddress().toString() + ":" + message);
            }
            out.close();
            in.close();
        } catch (IOException e) {
            System.out.println("Accept failed!");
            System.exit(1);
        }

    }
}
