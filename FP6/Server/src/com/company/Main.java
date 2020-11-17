package com.company;

import java.io.IOException;
import java.net.*;

public class Main {

    public static int sumOfRoundTripTimes = 0;

    private static DatagramSocket multicastSocket = null;
    private static int roundTripTime = 0;

    public static void main(String[] args) throws IOException, InterruptedException {
        // write your code here
        multicastSocket = new DatagramSocket(4445);
        AwaitResponseThread responseThread;
        while (true) {
            roundTripTime = calculateRoundTrip();
            sendDateTimeRequest();
            responseThread = new AwaitResponseThread();
            responseThread.start();
            Thread.sleep(10000);
            responseThread.interrupt();
            Thread.sleep(20000);
        }
    }

    public static int calculateRoundTrip() throws InterruptedException, IOException {
        Thread.sleep(5000);

        int countPacketsLosted = 0;

        for (int i = 0; i < 10; i++) {
            CalculateRoundTripThread tripThread = new CalculateRoundTripThread(multicastSocket);
            tripThread.start();
            Thread.sleep(5000);
            if(tripThread.isAlive()){
                tripThread.interrupt();
                countPacketsLosted++;
            }
        }

        System.out.println("RTT: " + (sumOfRoundTripTimes / 10) + "ms!");
        System.out.println("Pacotes Perdidos: " + countPacketsLosted);
        return (sumOfRoundTripTimes / 10);
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
