package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.SQLOutput;

public class PrinterThread extends Thread {

    private Socket socket;
    BufferedReader in = null;

    public PrinterThread(Socket socket) {
        super("PrinterThread");
        this.socket = socket;
    }

    @Override
    public void run() {
        super.run();
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = null;
            while ((message = in.readLine()) != null) {
                System.out.println("Mensagem: " + message);
            }
        } catch (IOException e) {
            System.out.println("Sessão Encerrada!");
        }
    }
}
