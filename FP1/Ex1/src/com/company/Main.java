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
        PrintWriter out = null;
        BufferedReader in = null;

        try{
            echoSocket = new Socket("127.0.0.1", 7);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don´t know about the host!");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn´t get I/O for the connection.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        while ((userInput = stdIn.readLine()) != null){
            out.println(userInput);
            if(userInput.equals("QUIT")) {
                break;
            }
            System.out.println("Echo: " + in.readLine());
        }

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}
