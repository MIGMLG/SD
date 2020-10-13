package com.company;

public class Main {

    public static void main(String[] args) {
        // write your code here
        int[] lista = {12, 5, -21, 10, -345, 22, 50, -125, 80, -1};

        int multi = 1;
        int countNegative = 0;
        int biggest = 0;

        for (int i = 0; i < lista.length; i++) {

            if(lista[i] > biggest){
                biggest = lista[i];
            }

            if (lista[i] > 0) {
                multi = multi * lista[i];
            } else {
                countNegative++;
            }


        }

        System.out.println("Mutiplicação dos números positivos: " + multi);
        System.out.println("Números negativos: " + countNegative);
        System.out.println("Maior número: " + biggest);

    }
}
