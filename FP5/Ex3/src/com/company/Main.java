package com.company;

import java.io.IOException;
import java.net.*;

public class Main {

    private static DatagramSocket multicastSocket = null;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        multicastSocket = new DatagramSocket(4445);

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

    public static void sendMessage(String text){
        byte[] buf = new byte[256];
        String message = text;
        buf = message.getBytes();

        InetAddress group = null;
        try {
            group = InetAddress.getByName("230.0.0.1");
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
        DatagramPacket packet;
        packet = new DatagramPacket(buf, buf.length, group, 4446);
        try {
            multicastSocket.send(packet);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
