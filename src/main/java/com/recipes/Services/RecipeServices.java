package com.recipes.Services;

import com.recipes.DTO.Recipe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServices implements IRecipeServices {

    private List<Recipe> recipeList = new ArrayList<>();

    @Override
    public void save(Recipe recipe) {
        if(!recipe.hasAllParameters()) {
            throw new IllegalArgumentException("All the parameters must not be nulls or empties");
        }
        recipeList.add(recipe);
    }

    @Override
    public List<Recipe> getRecipeList() {
        return recipeList;
    }
}
