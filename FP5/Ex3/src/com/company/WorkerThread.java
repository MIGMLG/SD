package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WorkerThread extends Thread {
    private Socket socket;
    private BufferedReader in;

    public WorkerThread(Socket socket) {
        super("WorkerThread");
        this.socket = socket;
    }

    @Override
    public void run() {
        super.run();
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

            String message = null;
            while ((message = in.readLine()) != null) {
                System.out.println("Mensagem do Cliente: " + message);
                LocalDateTime now = LocalDateTime.now();
                String text = "" + dtf.format(now) + " " + socket.getLocalAddress().toString() + ":" + message;
                Main.sendMessage(text);
                if (message.equals("Bye")) {
                    break;
                }
            }
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
