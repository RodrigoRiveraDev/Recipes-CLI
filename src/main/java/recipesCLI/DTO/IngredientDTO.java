package recipesCLI.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ingredient")
@XmlAccessorType(XmlAccessType.FIELD)
public class IngredientDTO implements IJSON {

    private String name;
    private double quantity;
    private String unit;

    /**
     * @return It will return the current quantity value
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity The value param that will replace the actual quantity value
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return It will return the current name value
     */
    public String getName() {
        return name;
    }

    /**
     * @param quantity The value param that will replace the actual name value
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return It will return the current unit value
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param quantity The value param that will replace the actual unit value
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return It will return the object as a String with Json format
     */
    @Override
    public String toString() {
        return "{"+
                "\"name\" : \"" + name + "\"" +
                ",\"quantity\" : \"" + quantity + "\"" +
                ",\"unit\" : \"" + unit + "\""
                +"}";
    }

    /**
     * @return It will return the object as a String with Json format
     */
    @Override
    public String toJSON() {
        return this.toString();
    }
}
