package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int NThreads = 5;
        ArrayList asFrases = new ArrayList();

        for (int i = 0; i < NThreads; i++) {
            new Worker(asFrases, i).start();
        }

        for (int j = 0; j < 6; j++) {
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            for (int k = 0; k < asFrases.size(); k++)
                System.out.println(asFrases.get(k));
        }
    }

}
