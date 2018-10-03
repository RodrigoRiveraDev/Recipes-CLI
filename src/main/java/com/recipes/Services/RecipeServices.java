package com.recipes.Services;

import com.recipes.DTO.Recipe;
import com.recipes.Exceptions.ResourceNotFoundException;
import com.recipes.Exceptions.UnauthorizedException;
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
    public Recipe updateRecipeInfo(long id, Recipe dataToUpdate, long userId) {
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

        if(!foundedRecipe.isOwner(userId)) {
            throw new UnauthorizedException();
        }

        foundedRecipe.updateInfo(dataToUpdate);
        return foundedRecipe;
    }

    @Override
    public void deleteRecipe(long userId, long recipeId) {
        long foundedRecipeIndex = -1;
        int index = recipeList.size();
        if(recipeId < 0) {
            throw new IllegalArgumentException("Negative id is not valid");
        }

        while(foundedRecipeIndex == -1 && --index >= 0) {
            foundedRecipeIndex = (recipeList.get(index).hasId(recipeId)) ? index : -1;
        }

        if(foundedRecipeIndex == -1) {
            throw new ResourceNotFoundException(Recipe.class, recipeId);
        }

        Recipe foundedRecipe = recipeList.get((int)foundedRecipeIndex);

        if(!foundedRecipe.isOwner(userId)) {
            throw new UnauthorizedException();
        }

        recipeList.remove(foundedRecipe);
    }

    @Override
    public Recipe getRecipeById(int id) {
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

        return foundedRecipe;
    }

    @Override
    public List<Recipe> getRecipeList() {
        return recipeList;
    }
}
