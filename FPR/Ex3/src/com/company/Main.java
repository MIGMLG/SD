package com.company;

public class Main {

    public static void main(String[] args) {
        // write your code here
        char[] nome = {'A', 'n', 'a', ' ', 'S', 'a', 'n', 't', 'o', 's',
                '\n'};

        int position = 0;
        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < nome.length; i++) {

            if (nome[i] == 'a' || nome[i] == 'e' || nome[i] == 'i'
                    || nome[i] == 'o' || nome[i] == 'u' || nome[i] == 'A' || nome[i] == 'E' || nome[i] == 'I'
                    || nome[i] == 'O' || nome[i] == 'U') {
                vowels++;
            } else if ((nome[i] >= 'a' && nome[i] <= 'z') || (nome[i] >= 'A' && nome[i] <= 'Z')) {
                consonants++;
            }

            if (nome[i] == ' ') {
                position = i;
            }

        }

        //Escrever nome
        for (int j = (position + 1); j < (nome.length - 1); j++) {
            System.out.print(nome[j]);
        }

        System.out.print(',');

        for (int h = 0; h < position; h++) {
            System.out.print(nome[h]);
        }

        System.out.println("\nNúmero de vogais: " + vowels);
        System.out.println("Número de consoantes: " + consonants);

    }
}
