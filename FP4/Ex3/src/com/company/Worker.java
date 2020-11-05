package com.company;

import java.util.ArrayList;

public class Worker extends Thread {
    SynchronizedArrayList frases;
    int numero;

    public Worker(SynchronizedArrayList f, int n) {
        super("Worker");
        this.frases = f;
        this.numero = n;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                frases.addObject("Frase " + i + " da thread " + numero);
                Thread.sleep(500 + i * 10);
            } catch (Exception ex) {
            }
        }
    }
}