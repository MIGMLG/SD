package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Date;

public class MulticastServerThread extends QuoteServerThread {

    private static final long FIVE_SECONDS =  50000;

    public MulticastServerThread() throws IOException {
        super("MulticastServerThread");
    }


    public void run() {
        while (moreQuotes) {
            try {
                byte[] buf = new byte[256];
                // don't wait for request...just send a quote

                String dString = null;

                if (in == null) {
                    dString = new Date().toString();
                } else {
                    dString = getNextQuote();
                }

                buf = dString.getBytes();

                InetAddress group = InetAddress.getByName("230.0.0.1");
                DatagramPacket packet;
                packet = new DatagramPacket(buf, buf.length, group, 4446);
                socket.send(packet);

                try {
                    Thread.sleep((long)(Math.random() * FIVE_SECONDS));
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                moreQuotes = false;
            }
        }
        socket.close();
    }
}
