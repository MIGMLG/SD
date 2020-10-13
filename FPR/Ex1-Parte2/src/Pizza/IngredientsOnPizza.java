package Pizza;

import Enums.Unit;

public class IngredientsOnPizza extends Ingredient {

    private float quantity;

    public IngredientsOnPizza(int id, String name, Unit unit, float calories, float quantity) {
        super(id, name, unit, calories);
        this.quantity = quantity;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "IngredientOnPizza{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", unit=" + this.getUnit().toString(this.getUnit()) +
                ", calories=" + this.getCalories() +
                "quantity=" + quantity +
                '}';
    }
}
