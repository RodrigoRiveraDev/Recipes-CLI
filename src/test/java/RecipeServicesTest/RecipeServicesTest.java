package RecipeServicesTest;

import com.recipes.DTO.Ingredient;
import com.recipes.DTO.Recipe;

import com.recipes.Exceptions.ResourceNotFoundException;
import com.recipes.Services.RecipeServices;
import com.recipes.Services.UserServices;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class RecipeServicesTest {

    @Test
    public void addNewRecipe() {
        RecipeServices recipeServices = new RecipeServices();
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Recipe newRecipe = new Recipe(ingredients, "steps");
        int savedRecipes;
        recipeServices.save(newRecipe);
        savedRecipes = recipeServices.getRecipeList().size();
        Assert.assertTrue(savedRecipes == 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNewRecipeThrowsIllegalArgumentException()  {
        RecipeServices recipeServices = new RecipeServices();
        recipeServices.save(new Recipe());
    }
}
