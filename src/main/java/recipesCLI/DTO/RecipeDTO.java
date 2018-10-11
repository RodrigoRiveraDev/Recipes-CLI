package recipesCLI.DTO;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSetter;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RecipeDTO implements IJSON {

    @JsonProperty
    private long id;
    @JsonProperty
    private List<IngredientDTO> ingredients;
    @JsonProperty
    private String howElaborate;
    @JsonProperty
    private long userId;

    @JsonCreator
    public RecipeDTO(
            @JsonProperty("ingredients") List<IngredientDTO> ingredients,
            @JsonProperty("howElaborate") String howElaborate) {
        this.ingredients = ingredients;
        this.howElaborate = howElaborate;
    }

    public RecipeDTO() {
        howElaborate = "";
        ingredients = new ArrayList<>();
    }

    /**
     * @return It will return the current id value
     */
    public long getId() {
        return id;
    }

    /**
     * @param id The value param that will replace the actual id value
     */
    @JsonSetter
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return It will return the current howElaborate value
     */
    public String getHowElaborate() {
        return howElaborate;
    }

    /**
     * @param howElaborate The value param that will replace the actual howElaborate value
     */
    @JsonSetter
    public void setHowElaborate(String howElaborate) {
        this.howElaborate = howElaborate;
    }

    /**
     * @return It will return the current ingredients list
     */
    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    /**
     * @param ingredients The value param that will replace the actual ingredients list
     */
    @JsonSetter
    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * @return It will return the current userId value
     */
    public long getUserId() {
        return userId;
    }

    /**
     * @param userID The value param that will replace the actual userId value
     */
    @JsonSetter
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * @return It will return the ingredients list as a String with Json format
     */
    private String ingredientsToJSON() {
        StringBuilder res = new StringBuilder();
        boolean first = true;
        for (IngredientDTO ingredient: ingredients) {
            if(first) {
                first = false;
            } else {
                res.append(",");
            }
            res.append(ingredient.toJSON());
        }
        return res.toString();
    }

    /**
     * @return It will return the object as a String with Json format
     */
    @Override
    public String toString() {
        return "{"+
                "\"id\" : \"" + id + "\"" +
                ",\"ingredients\" : [" + ingredientsToJSON() + "]" +
                ",\"howElaborate\" : \"" + howElaborate + "\"" +
                ",\"userId\" : \"" + userId + "\""
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
