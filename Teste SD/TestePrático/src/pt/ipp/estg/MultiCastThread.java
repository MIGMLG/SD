package pt.ipp.estg;

import java.io.IOException;
import java.net.*;

public class MultiCastThread extends Thread{

    private static DatagramSocket multicastSocket = null;

    public MultiCastThread() {
        super();
    }

    @Override
    public void run() {
        super.run();
        try {
            multicastSocket = new DatagramSocket(4445);
            byte[] buf = new byte[256];
            String message = "Send me your Data!";
            buf = message.getBytes();

            InetAddress group = null;
            try {
                group = InetAddress.getByName("230.0.0.1");
            } catch (UnknownHostException e) {
                System.out.println(e.getMessage());
            }

            Thread.sleep(10000);
            DatagramPacket packet;
            packet = new DatagramPacket(buf, buf.length, group, 4446);
            try {
                multicastSocket.send(packet);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            //Falta receber resposta
        } catch (SocketException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
