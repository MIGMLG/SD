package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.text.SimpleDateFormat;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Date;

public class PrinterThread extends Thread{

    private MulticastSocket socket;
    private DatagramPacket packet;
    private InetAddress group;
    private static final String SEND_DATA = "Send Me Your Data!";

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
            group = InetAddress.getByName("230.0.0.1");
            socket.joinGroup(group);

            byte[] buf = new byte[256];
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            while(packet != null){
                String received = new String(packet.getData());

                if(received.contains(SEND_DATA)) {
                    System.out.println("" + received);
                    sendDateTime();
                } else if(received.contains("Bye")) {
                    break;
                }
                //Debug Code
                //System.out.println("" + received);
                buf = new byte[256];
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
            }

            socket.leaveGroup(group);
            socket.close();
        } catch (IOException | InterruptedException e) {
            System.out.println("Sessão Encerrada!");
        }
    }

    private void sendDateTime() throws InterruptedException {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        byte[] buf = new byte[256];
        buf = formatter.format(date).getBytes();

        DatagramPacket packet;
        packet = new DatagramPacket(buf, buf.length, group, 4446);
        try {
            Thread.sleep(5000);
            socket.send(packet);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
