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
            socket = new MulticastSocket(4446);
            DatagramPacket packet;
            InetAddress group = InetAddress.getByName("230.0.0.1");
            socket.joinGroup(group);

            byte[] buf = new byte[256];
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            String received = new String(packet.getData());
            System.out.println("" + received);
            if(received.contains("Bye")) {
                interrupt();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
