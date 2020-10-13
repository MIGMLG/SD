package Pizza;

import Enums.Unit;

public class Ingredient {

    private int id;
    private String name;
    private Unit unit;
    private float calories;

    public Ingredient(int id, String name, Unit unit, float calories) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.calories = calories;
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unit=" + unit.toString(unit) +
                ", calories=" + calories +
                '}';
    }
}
