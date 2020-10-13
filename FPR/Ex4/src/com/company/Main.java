package com.company;

public class Main {

    public static void main(String[] args) {
        // write your code here
        if (args.length != 0 && args[0] != null && args[1] != null) {
            System.out.print(args[1] + "," + args[0]);
        } else {
            System.out.println("No arguments were found!");
        }
    }
}
