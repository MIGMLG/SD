package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
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
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Accept failed!");
            System.exit(1);
        }

        try {
            while (clientSocket.isConnected()) {
                String message = null;
                message = in.readLine();
                System.out.println("Mensagem do Cliente: " + message);
                if (message.equals("QUIT")) {
                    break;
                }
                out.println("" + serverSocket.getLocalSocketAddress().toString() + ":" + clientSocket.getLocalAddress().toString() + ":" + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
