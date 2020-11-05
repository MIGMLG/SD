package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.LinkedList;

public class Servidor {

    private static LinkedList<ProcessaCliente> chat = new LinkedList<>();
    private static ServerSocket serverSocket = null;
    protected static ArrayList<String> messages = new ArrayList<>();

    public static void start() throws IOException {

        Runnable waitingConnections = new Runnable() {
            public void run() {
                int port = 2048;
                boolean listening = true;

                try {
                    serverSocket = new ServerSocket(port);
                } catch (IOException e) {
                    System.err.println("Cloud not listen on port: " + port + ".");
                    System.exit(-1);
                }

                try {
                    while (listening) {
                        ProcessaCliente wt = new ProcessaCliente(serverSocket.accept());
                        wt.start();
                        chat.add(wt);
                    }
                    serverSocket.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        };
        new Thread(waitingConnections).start();

        Runnable sendingMessages = new Runnable() {
            public void run() {
                while (true){
                    if(!messages.isEmpty()){
                        String text = messages.get(0);
                        for (ProcessaCliente aChat : chat) {
                            aChat.sendMessage(text);
                        }
                        messages.remove(text);
                    }

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        new Thread(sendingMessages).start();
    }
}
