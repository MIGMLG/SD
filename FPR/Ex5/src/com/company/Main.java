package com.company;

public class Main {

    public static void main(String[] args) {
        // write your code here
        int[] listaA = {2, -5, -121, 102, -35, -2, 0, -125, 802, -
                10};
        int[] listaB = {6, 99, -1, 12, 1, -2};

        int[] listaResultado = new int[(listaA.length + listaB.length)];

        //Unir Vectores
        for (int i = 0; i < listaA.length; i++) {
            listaResultado[i] = listaA[i];
        }

        int count = listaA.length;

        for (int i = 0; i < listaB.length; i++) {
            listaResultado[count] = listaB[i];
            count++;
        }

        System.out.print("Lista Resultado: ");
        for (int i = 0; i < listaResultado.length; i++) {
            System.out.print("" + listaResultado[i] + ",");
        }

        //Contar repetidos

        int countRepetead = 0;

        for (int i = 0; i < listaResultado.length; i++) {
            for (int j = i; j < listaResultado.length; j++) {
                if (i != j && listaResultado[i] == listaResultado[j]) {
                    countRepetead++;
                }
            }
        }

        System.out.println("\nRepetidos na listaA: " + countRepetead);

        //Preencher novo vetor só com números diferentes

        int[] listaRA = new int[(listaA.length - countRepetead)];
        int countItr = 0;

        for (int i = 0; i < listaA.length; i++) {
            boolean check = true;

            for (int j = 0; j < listaB.length; j++) {
                if (listaA[i] == listaB[j]) {
                    check = false;
                }
            }

            if (check){
                listaRA[countItr] = listaA[i];
                countItr++;
            }
        }

        //Preencher novo vetor só com os números iguais

        int countItrE = 0;

        int[] listaRR = new int[(countRepetead)];
        for (int i = 0; i < listaA.length; i++) {
            boolean check = false;

            for (int j = 0; j < listaB.length; j++) {
                if (listaA[i] == listaB[j]) {
                    check = true;
                    break;
                }
            }

            if (check){
                listaRR[countItrE] = listaA[i];
                countItrE++;
            }
        }
    }
}
