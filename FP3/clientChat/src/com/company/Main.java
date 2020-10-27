package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        Socket echoSocket = null;

        try {
            echoSocket = new Socket("127.0.0.1", 2048);
            WriterThread wt = new WriterThread(echoSocket);
            wt.start();
            PrinterThread pt = new PrinterThread(echoSocket);
            pt.start();
        } catch (UnknownHostException e) {
            System.err.println("Don´t know about the host!");
            System.exit(1);
        }

    }
}
