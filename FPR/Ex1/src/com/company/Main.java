package com.company;

import java.sql.SQLOutput;

public class Main {

    private static final int[][] multi = new int[][]{
            {11, 7, 333},
            {-20, -23, 63},
            {-22, 501, 10000}
    };

    public static void main(String[] args) {
        // write your code here
        int sum = 0;
        int count = 0;

        for (int i = 0; i < multi.length; i++) {
            for (int j = 0; j < multi[0].length; j++) {
                sum += multi[i][j];
                count++;
            }
        }

        //Perda de precisão se não for usado cast
        float average = (float) sum / count;

        System.out.println("A soma dos valores é: " + sum);
        System.out.println("A média dos valores é: " + average);

    }
}
