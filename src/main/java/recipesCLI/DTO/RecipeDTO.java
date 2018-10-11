package recipesCLI.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "recipe")
@XmlAccessorType(XmlAccessType.FIELD)
public class RecipeDTO implements IJSON {

    private long id;
    private List<IngredientDTO> ingredients;
    private String howElaborate;
    private long userId;

    public RecipeDTO() {
        ingredients = null;
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
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public RecipeDTO(List<IngredientDTO> ingredients, String howElaborate) {
        this.ingredients = ingredients;
        this.howElaborate = howElaborate;
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
