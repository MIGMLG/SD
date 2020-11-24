package com.company;

import java.io.IOException;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static List<Client> clientList;
    private static int roundTripTime = 0;

    public static void main(String[] args) throws IOException, InterruptedException {
        // write your code here
        ClientThread clientThread = new ClientThread();
        clientThread.start();
        Thread.sleep(1000);
        System.out.println("---------------------------------------------------");
        System.out.println("Pedido Enviado aos Clientes");
        sendDateTimeRequest();
        System.out.println("---------------------------------------------------");
        System.out.println("Resposta dos Clientes : ");
        awaitResponse();
        System.out.println("---------------------------------------------------");
        System.out.println("Calculo Round Trip Time : ");
        Thread.sleep(5000);
        roundTripTime = calculateRoundTrip();
        Thread.sleep(5000);
        System.out.println("---------------------------------------------------");
        System.out.println("Calculo de Atualização de Datas : ");
        updateDates();
        System.out.println("---------------------------------------------------");
    }

    public static void sendDateTimeRequest() throws IOException {
        MulticastSocket multicastSocket = new MulticastSocket(4445);

        byte[] buf;
        String message = "Send Me Your Data!";
        buf = message.getBytes();
        InetAddress group = null;
        try {
            group = InetAddress.getByName("230.0.0.1");
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
        DatagramPacket packet;
        packet = new DatagramPacket(buf, buf.length, group, 4446);
        DatagramPacket packetServer = new DatagramPacket(buf, buf.length, group, 4449);
        try {
            multicastSocket.send(packet);
            multicastSocket.send(packetServer);
            multicastSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void awaitResponse() throws SocketException {
        Main.clientList = new ArrayList<>();
        DatagramSocket socket = new DatagramSocket(4445);
        DatagramPacket packet;

        while (true) {
            try {
                byte[] buf = new byte[256];
                packet = new DatagramPacket(buf, buf.length);
                socket.setSoTimeout(5000);
                socket.receive(packet);
                String received = new String(packet.getData());
                Client client = new Client(packet.getAddress().toString().substring(1), packet.getPort(), received);
                Main.clientList.add(client);

                System.out.println("- Client on Await : " + client.getAddress() + ":" + client.getPort());
                System.out.println("- Client on Await : " + received);


            } catch (IOException | ParseException e) {
                socket.close();
                System.out.println("Socket Request Hour Fechado!");
                break;
            }

        }
    }

    public static int calculateRoundTrip() {
        int countPacketsLosted = 0;
        int sumOfRoundTripTimes = 0;

        for (int i = 0; i < clientList.size(); i++) {
            Client client = clientList.get(i);

            try {
                byte[] buf;
                buf = "PING!".getBytes();
                InetAddress address = null;
                address = InetAddress.getByName(client.getAddress());

                DatagramPacket packet;
                packet = new DatagramPacket(buf, buf.length, address, client.getPort());

                DatagramSocket socket;
                socket = new DatagramSocket(4445);

                long startTime = System.currentTimeMillis();
                socket.send(packet);

                buf = new byte[256];
                packet = new DatagramPacket(buf, buf.length);
                socket.setSoTimeout(5000);
                socket.receive(packet);

                long endTime = System.currentTimeMillis();

                sumOfRoundTripTimes += (endTime - startTime);
                socket.close();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                countPacketsLosted++;
                System.out.println("Socket Ping Timed Out!");
            }
        }

        System.out.println("RTT: " + (sumOfRoundTripTimes / clientList.size()) + "ms!");
        System.out.println("Pacotes Perdidos: " + countPacketsLosted);
        return (sumOfRoundTripTimes / clientList.size());
    }

    public static void updateDates() {
        System.out.println("Número de clientes : " + clientList.size());

        long sum = 0;

        for (int i = 0; i < clientList.size(); i++) {
            Client client = clientList.get(i);
            sum += client.getDate().getTime();
            System.out.println("Cliente " + (i + 1) + " : " + client.getPort());
        }

        long avg = sum / clientList.size();
        long finalTime = avg + roundTripTime;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(finalTime);

        System.out.println("Date final: " + date);

        for (int i = 0; i < clientList.size(); i++) {
            sendNewDate(formatter.format(date), clientList.get(i).getAddress(), clientList.get(i).getPort());
        }


    }

    public static void sendNewDate(String date, String address, int port) {
        byte[] buf;
        buf = date.getBytes();

        try {
            InetAddress clientAddress = InetAddress.getByName(address);

            DatagramPacket packet;
            packet = new DatagramPacket(buf, buf.length, clientAddress, port);

            DatagramSocket socket = new DatagramSocket(4445);
            socket.send(packet);
            socket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
