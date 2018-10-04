package DTO;

public class IngredientDTO implements IJSON {

    private String name;
    private double quantity;
    private String unit;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "{"+
                "\"name\" : \"" + name + "\"" +
                ",\"quantity\" : \"" + quantity + "\"" +
                ",\"unit\" : \"" + unit + "\""
                +"}";
    }

    @Override
    public String toJSON() {
        return this.toString();
    }
}
