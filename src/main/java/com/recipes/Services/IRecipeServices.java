package com.recipes.Services;

import com.recipes.DTO.Recipe;

import java.util.List;

public interface IRecipeServices {

    void save(Recipe recipe);

    List<Recipe> getRecipeList();

    Recipe updateRecipeInfo(long id, Recipe dataToUpdate, long userId);

    void deleteRecipe(long userId, long id);

    Recipe getRecipeById(int id);
}
