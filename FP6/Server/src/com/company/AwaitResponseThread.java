package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.ArrayList;

public class AwaitResponseThread extends Thread {

    @Override
    public void run() {
        super.run();

        MulticastSocket socket = null;
        Main.clientList = new ArrayList<>();

        try {
            socket = new MulticastSocket(4447);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DatagramPacket packet;
        InetAddress group = null;
        try {
            group = InetAddress.getByName("230.0.0.1");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            socket.joinGroup(group);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                byte[] buf = new byte[256];
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(packet.getData());
                Client client = new Client(packet.getAddress().toString().substring(1), packet.getPort(), received);
                Main.clientList.add(client);

                System.out.println("Client on Await : " + client.getAddress() + ":" + client.getPort());
                System.out.println("Client on Await : " + received);

                //socket.leaveGroup(group);
                //socket.close();

            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }

        }
    }

}
