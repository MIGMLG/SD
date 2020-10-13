package com.company;

import Enums.Unit;
import Pizza.Ingredient;
import Pizza.IngredientsOnPizza;
import Pizza.Pizza;

import static Enums.Size.*;
import static Enums.Unit.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("--------------------------------------------------------------------------");
        Ingredient ig1 = new Ingredient(1, "Tomato", GRAMS, (float) 15.7);
        System.out.println(ig1.toString());
        IngredientsOnPizza igp1 = new IngredientsOnPizza(1, "Tomato", GRAMS, (float) 15.7, 15);
        System.out.println(igp1.toString());
        System.out.println("--------------------------------------------------------------------------");
        Pizza p1 = new Pizza(1, "I dont know!", "I dont know!", 15.4f, KING);
        System.out.println(p1.toString());
        System.out.println("--------------------------------------------------------------------------");
        for (int i = 0; i < 6; i++){
            System.out.println("Resultado: " + p1.addIgredient(igp1));
        }
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Resultado da mudança de quantidade: " + p1.changeQuantityOfIngredient(2, 2.0f));
        System.out.println("Resultado da mudança de quantidade: " + p1.changeQuantityOfIngredient(1, 2.0f));
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Resultado da eliminação do ingrediente: " + p1.removeIngredient(2));
        System.out.println("Resultado da eliminação do ingrediente: " + p1.removeIngredient(1));
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Quantidade de calorias: " + p1.getAmountOfCalories());
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(p1.toString());
    }
}
