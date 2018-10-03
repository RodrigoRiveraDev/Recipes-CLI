package com.recipes.Services;

import com.recipes.DTO.Recipe;
import com.recipes.Exceptions.ResourceNotFoundException;
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
    public Recipe updateRecipeInfo(long id, Recipe dataToUpdate) {
        Recipe foundedRecipe = null;
        int index = recipeList.size();
        if(id < 0) {
            throw new IllegalArgumentException("Negative id is not valid");
        }

        while(foundedRecipe == null && --index >= 0) {
            foundedRecipe = (recipeList.get(index).hasId(id)) ? recipeList.get(index) : null;
        }

        if(foundedRecipe == null) {
            throw new ResourceNotFoundException(Recipe.class, id);
        }

        foundedRecipe.updateInfo(dataToUpdate);
        return foundedRecipe;
    }

    @Override
    public List<Recipe> getRecipeList() {
        return recipeList;
    }
}
