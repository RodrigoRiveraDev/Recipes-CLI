package DTO;

import java.util.ArrayList;
import java.util.List;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHowElaborate() {
        return howElaborate;
    }

    public void setHowElaborate(String howElaborate) {
        this.howElaborate = howElaborate;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public RecipeDTO(List<IngredientDTO> ingredients, String howElaborate) {
        this.ingredients = ingredients;
        this.howElaborate = howElaborate;
    }

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

    @Override
    public String toString() {
        return "{"+
                "\"id\" : \"" + id + "\"" +
                ",\"ingredients\" : [" + ingredientsToJSON() + "]" +
                ",\"howElaborate\" : \"" + howElaborate + "\"" +
                ",\"userId\" : \"" + userId + "\""
                +"}";
    }

    @Override
    public String toJSON() {
        return this.toString();
    }
}
