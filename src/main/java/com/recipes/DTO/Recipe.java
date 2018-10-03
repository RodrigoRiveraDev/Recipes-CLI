package com.recipes.DTO;

import java.util.List;

public class Recipe {

    private long id;
    private List<Ingredient> ingredients;
    private String howElaborate;

    public Recipe() {}

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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public boolean hasAllParameters() {
        return  !howElaborate.isEmpty() &&
                ingredients.size() > 0;
    }
}
