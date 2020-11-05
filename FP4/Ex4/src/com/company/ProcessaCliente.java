package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProcessaCliente extends Thread {
    private Socket socket = null;
    PrintWriter out = null;
    BufferedReader in = null;

    public ProcessaCliente(Socket socket) {
        super("WorkerThread");
        this.socket = socket;
    }

    @Override
    public void run() {
        super.run();
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

            String message = null;
            while ((message = in.readLine()) != null) {
                System.out.println("Mensagem do Cliente: " + message);
                LocalDateTime now = LocalDateTime.now();
                String text = "" + dtf.format(now) + " " + socket.getLocalAddress().toString() + ":" + message;
                //Servidor.sendBroadcast(text, this);
                Servidor.messages.add(text);
                if (message.equals("Bye")) {
                    break;
                }
            }
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String text){
        out.println(text);
    }
}
