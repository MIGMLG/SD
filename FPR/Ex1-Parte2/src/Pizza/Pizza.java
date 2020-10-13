package Pizza;

import Enums.Size;

public class Pizza {

    private static final int MAX_INGREDIENTS = 5;

    private int id;
    private String name;
    private String description;
    private float price;
    private Size size;
    private int numbersOfIngredients;
    private IngredientsOnPizza[] ingredients;

    public Pizza(int id, String name, String description, float price, Size size) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.size = size;
        this.numbersOfIngredients = 0;
        this.ingredients = new IngredientsOnPizza[MAX_INGREDIENTS];
    }

    public boolean addIgredient(IngredientsOnPizza ig) {
        if (numbersOfIngredients < MAX_INGREDIENTS) {
            ingredients[numbersOfIngredients] = ig;
            numbersOfIngredients++;
            return true;
        } else {
            return false;
        }
    }

    public boolean changeQuantityOfIngredient(int id, float quant) {
        int position = this.searchForIngredient(id);

        if (position == -1) {
            return false;
        } else {
            ingredients[position].setQuantity(quant);
            return true;
        }
    }

    public boolean removeIngredient(int id) {
        int position = this.searchForIngredient(id);

        if (position == -1) {
            return false;
        } else {
            for (int i = position; i < (numbersOfIngredients - 1); i++) {
                ingredients[i] = ingredients[i + 1];
            }

            ingredients[(numbersOfIngredients - 1)] = null;
            numbersOfIngredients--;

            return true;
        }
    }

    private int searchForIngredient(int id) {
        int postion = -1;

        for (int i = 0; i < numbersOfIngredients; i++) {
            if (ingredients[i].getId() == id) {
                postion = i;
                break;
            }
        }

        return postion;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getNumbersOfIngredients() {
        return numbersOfIngredients;
    }

    public float getAmountOfCalories(){
        float sum = 0;
        for ( int i = 0; i < numbersOfIngredients; i++){
            sum+= (ingredients[i].getCalories() * ingredients[i].getQuantity());
        }
        return sum;
    }

    private String ingredientsToString(){
        String text = "";
        for ( int i = 0; i < numbersOfIngredients; i++){
            text+="{";
            text+= ingredients[i].toString();
            text+="}";
        }
        return text;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", size=" + size +
                ", numbersOfIngredients=" + numbersOfIngredients +
                ", ingredients={" + this.ingredientsToString()+ "}" +
                '}';
    }
}
