package recipesCLI.DTO;

import org.codehaus.jackson.annotate.*;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class IngredientDTO implements IJSON {

    @JsonProperty
    private String name;
    @JsonProperty
    private double quantity;
    @JsonProperty
    private String unit;

    public IngredientDTO() {}

    @JsonCreator
    public IngredientDTO(
            @JsonProperty("name") String name,
            @JsonProperty("quantity") double quantity,
            @JsonProperty("unit") String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    /**
     * @return It will return the current quantity value
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity The value param that will replace the actual quantity value
     */
    @JsonSetter
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
     * @param name The value param that will replace the actual name value
     */
    @JsonSetter
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
     * @param unit The value param that will replace the actual unit value
     */
    @JsonSetter
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
