package com.recipes.Services;

import com.recipes.DTO.Recipe;

import java.util.List;

public interface IRecipeServices {

    void save(Recipe recipe);

    List<Recipe> getRecipeList();
}
