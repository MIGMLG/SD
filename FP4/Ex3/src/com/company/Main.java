package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int NThreads = 5;
        SynchronizedArrayList asFrases = new SynchronizedArrayList();

        for (int i = 0; i < NThreads; i++) {
            new Worker(asFrases, i).start();
        }

        for (int j = 0; j < 6; j++) {
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            ArrayList frases = asFrases.get();
            for (int k = 0; k < frases.size(); k++)
                System.out.println(frases.get(k));
        }
    }
}
