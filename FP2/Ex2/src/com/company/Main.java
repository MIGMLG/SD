package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

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
        KnockKnockProtocol knock = new KnockKnockProtocol();

        try {
            clientSocket = serverSocket.accept();
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Accept failed!");
            System.exit(1);
        }

        try {
            String messageSent = knock.processInput("");
            out.println(messageSent);
            String messageReceived = null;
            while ((messageReceived = in.readLine()) != null) {
                System.out.println("Mensagem do Cliente: " + messageReceived);
                messageSent = knock.processInput(messageReceived);
                System.out.println("Mensagem do Enviada: " + messageSent);
                out.println(messageSent);
                if (messageReceived.equals("n")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
