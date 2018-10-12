package recipesCLI.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "unit",
        "quantity"
})
public class IngredientDTO implements IJSON {

    @JsonProperty("id")
    private long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("quantity")
    private double quantity;

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
     * @return It will return the current id value
     */
    @JsonProperty("id")
    public long getId() {
        return id;
    }

    /**
     * @param id The value param that will replace the actual id value
     */
    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return It will return the current name value
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * @param name The value param that will replace the actual name value
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return It will return the current unit value
     */
    @JsonProperty("unit")
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit The value param that will replace the actual unit value
     */
    @JsonProperty("unit")
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return It will return the current quantity value
     */
    @JsonProperty("quantity")
    public double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity The value param that will replace the actual quantity value
     */
    @JsonProperty("quantity")
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return It will return the object as a String with Json format
     */
    @Override
    public String toJSON() {
        return "{"+
                "\"name\" : \"" + name + "\"" +
                ",\"quantity\" : \"" + quantity + "\"" +
                ",\"unit\" : \"" + unit + "\""
                +"}";
    }
}