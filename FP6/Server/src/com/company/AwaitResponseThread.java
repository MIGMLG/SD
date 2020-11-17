package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class AwaitResponseThread extends Thread{

    @Override
    public void run() {
        super.run();
        MulticastSocket socket = null;
        try {
            socket = new MulticastSocket(4447);
            DatagramPacket packet;
            InetAddress group = InetAddress.getByName("230.0.0.1");
            socket.joinGroup(group);

            byte[] buf = new byte[256];
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            System.out.println("Client: " + packet.getAddress() + ":" + packet.getPort());

            String received = new String(packet.getData());
            System.out.println("" + received);

            socket.leaveGroup(group);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
