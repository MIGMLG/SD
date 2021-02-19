package pt.ipp.estg;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MultiCastThread extends Thread{

    private MulticastSocket socket;
    private DatagramPacket packet;

    public MultiCastThread() {
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
                //Falta mandar resposta do dados
            }
        } catch (IOException e) {
            System.out.println("Sessão Encerrada!");
        }
    }
}
