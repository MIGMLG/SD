package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try {
            new MulticastServerThread().start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
