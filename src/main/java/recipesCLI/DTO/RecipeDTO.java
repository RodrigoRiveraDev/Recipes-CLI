package recipesCLI.DTO;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "howElaborate",
        "ingredients",
        "userId"
})
public class RecipeDTO implements IJSON {

    @JsonProperty("id")
    private long id;
    @JsonProperty("howElaborate")
    private String howElaborate;
    @JsonProperty("ingredients")
    private List<IngredientDTO> ingredientsDTO;
    @JsonProperty("userId")
    private long userId;

    public RecipeDTO() {
        ingredientsDTO = new ArrayList<>();
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
     * @return It will return the current userId value
     */
    @JsonProperty("userId")
    public long getUserId() {
        return userId;
    }

    /**
     * @param id The value param that will replace the actual id value
     */
    @JsonProperty("userId")
    public void setUserId(long id) {
        this.userId = id;
    }

    /**
     * @return It will return the current howElaborate value
     */
    @JsonProperty("howElaborate")
    public String getHowElaborate() {
        return howElaborate;
    }

    /**
     * @param howElaborate The value param that will replace the actual howElaborate value
     */
    @JsonProperty("howElaborate")
    public void setHowElaborate(String howElaborate) {
        this.howElaborate = howElaborate;
    }

    /**
     * @return It will return the current ingredients list
     */
    @JsonProperty("ingredients")
    public List<IngredientDTO> getIngredients() {
        return ingredientsDTO;
    }

    /**
     * @param ingredientsDTO The value param that will replace the actual ingredients list
     */
    @JsonProperty("ingredients")
    public void setIngredients(List<IngredientDTO> ingredientsDTO) {
        this.ingredientsDTO = ingredientsDTO;
    }

    /**
     * @return It will return the ingredients list as a String with Json format
     */
    private String ingredientsToJSON() {
        StringBuilder res = new StringBuilder();
        boolean first = true;
        for (IngredientDTO ingredient: ingredientsDTO) {
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
    public String toJSON() {
        return "{"+
                "\"id\" : \"" + id + "\"" +
                ",\"ingredients\" : [" + ingredientsToJSON() + "]" +
                ",\"howElaborate\" : \"" + howElaborate + "\"" +
                ",\"userId\" : \"" + userId + "\""
                +"}";
    }
}
