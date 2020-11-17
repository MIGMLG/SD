package com.company;

import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static int sumOfRoundTripTimes = 0;
    public static List<Client> clientList;

    private static DatagramSocket multicastSocket = null;
    private static int roundTripTime = 0;

    public static void main(String[] args) throws IOException, InterruptedException {
        // write your code here
        multicastSocket = new DatagramSocket(4445);
        AwaitResponseThread responseThread;
        roundTripTime = calculateRoundTrip();
        ClientThread clientThread  = new ClientThread();
        clientThread.start();
        responseThread = new AwaitResponseThread();
        responseThread.start();
        sendDateTimeRequest();
        Thread.sleep(10000);
        updateDates();
        Thread.sleep(10000);
        responseThread.interrupt();
        clientThread.interrupt();
        System.exit(0);
    }

    public static int calculateRoundTrip() throws InterruptedException, IOException {
        Thread.sleep(5000);

        int countPacketsLosted = 0;

        for (int i = 0; i < 10; i++) {
            CalculateRoundTripThread tripThread = new CalculateRoundTripThread(multicastSocket);
            tripThread.start();
            Thread.sleep(5000);
            if (tripThread.isAlive()) {
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
        DatagramPacket packetServer = new DatagramPacket(buf, buf.length, group,4449);
        try {
            multicastSocket.send(packet);
            multicastSocket.send(packetServer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateDates() {

        System.out.println("Size: " + clientList.size());

        long sum = 0;

        for (int i = 0; i < clientList.size(); i++) {
            Client client = clientList.get(i);
            sum += client.getDate().getTime();
            System.out.println("Client " + i + " : " + client.getPort());
        }

        long avg = sum / clientList.size();
        long finalTime = avg + roundTripTime;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(finalTime);

        for (int i = 0; i < clientList.size(); i++) {
            sendNewDate(formatter.format(date), clientList.get(i).getAddress(), clientList.get(i).getPort());
        }


    }

    public static void sendNewDate(String date, String address, int port) {

        byte[] buf = new byte[256];
        buf = date.getBytes();

        try {
            InetAddress group = InetAddress.getByName(address);

            DatagramPacket packet;
            packet = new DatagramPacket(buf, buf.length, group, port);

            multicastSocket.send(packet);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
