package com.company;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class Main {

    private static DatagramSocket multicastSocket = null;

    public static void main(String[] args) throws IOException, InterruptedException {
	// write your code here
        multicastSocket = new DatagramSocket(4445);
        AwaitResponseThread responseThread;
        while(true){
            sendDateTimeRequest();
            responseThread = new AwaitResponseThread();
            responseThread.start();
            Thread.sleep(10000);
            responseThread.interrupt();
            Thread.sleep(20000);
        }
    }

    public static void sendDateTimeRequest() throws InterruptedException {
        byte[] buf = new byte[256];
        String message = "Send Me Your Data!";
        buf = message.getBytes();
        Thread.sleep(5000);
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
