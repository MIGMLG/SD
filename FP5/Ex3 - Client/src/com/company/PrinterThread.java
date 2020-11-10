package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

public class PrinterThread extends Thread{

    private MulticastSocket socket;
    private DatagramPacket packet;

    public PrinterThread() {
        super("PrinterThread");
        try {
            this.socket = new MulticastSocket(4446);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        super.run();
        try {
            InetAddress group = InetAddress.getByName("230.0.0.1");
            socket.joinGroup(group);

            while(true){
                byte[] buf = new byte[256];
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String received = new String(packet.getData());
                System.out.println("" + received);
                if(received.contains("Bye")) {
                    break;
                }
            }

            socket.leaveGroup(group);
            socket.close();
        } catch (IOException e) {
            System.out.println("Sessão Encerrada!");
        }
    }
}
