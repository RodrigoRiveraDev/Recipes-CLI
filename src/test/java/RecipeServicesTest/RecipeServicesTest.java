package RecipeServicesTest;

import com.recipes.DTO.Ingredient;
import com.recipes.DTO.Recipe;

import com.recipes.Exceptions.ResourceNotFoundException;
import com.recipes.Exceptions.UnauthorizedException;
import com.recipes.Services.RecipeServices;
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

    @Test(expected = IllegalArgumentException.class)
    public void updateRecipeThrowsIllegalArgumentException()  {
        RecipeServices recipeServices = new RecipeServices();
        recipeServices.updateRecipeInfo(-1, new Recipe(), 4);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateRecipeThrowsResourceNotFoundException()  {
        RecipeServices recipeServices = new RecipeServices();
        recipeServices.updateRecipeInfo(0, new Recipe(), 4);
    }

    @Test(expected = UnauthorizedException.class)
    public void updateRecipeThrowsUnauthorizedException() {
        RecipeServices recipeServices = new RecipeServices();
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Recipe newRecipe = new Recipe(ingredients, "steps");
        newRecipe.setId(1);
        newRecipe.setUserId(2);
        recipeServices.save(newRecipe);
        Recipe updateInfo = new Recipe(new ArrayList<Ingredient>(), "new step");
        recipeServices.updateRecipeInfo(1, updateInfo, 5);
    }

    @Test
    public void updateRecipe() {
        RecipeServices recipeServices = new RecipeServices();
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Recipe newRecipe = new Recipe(ingredients, "steps");
        newRecipe.setId(1);
        newRecipe.setUserId(2);
        recipeServices.save(newRecipe);
        Recipe updateInfo = new Recipe(new ArrayList<Ingredient>(), "new step");
        Recipe updatedRecipe = recipeServices.updateRecipeInfo(1, updateInfo, 2);
        updateInfo.setId(1);
        updateInfo.setIngredients(ingredients);
        Assert.assertTrue(updatedRecipe.equals(updateInfo));
    }

    @Test
    public void deleteRecipe() {
        RecipeServices recipeServices = new RecipeServices();
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Recipe newRecipe = new Recipe(ingredients, "steps");
        newRecipe.setId(1);
        newRecipe.setUserId(2);
        recipeServices.save(newRecipe);
        recipeServices.deleteRecipe(2, 1);
        int savedRecipes = recipeServices.getRecipeList().size();
        Assert.assertTrue(savedRecipes == 0);
    }

    @Test(expected = UnauthorizedException.class)
    public void deleteRecipeThrowsUnauthorizedException() {
        RecipeServices recipeServices = new RecipeServices();
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Recipe newRecipe = new Recipe(ingredients, "steps");
        newRecipe.setId(1);
        newRecipe.setUserId(2);
        recipeServices.save(newRecipe);
        recipeServices.deleteRecipe(1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteRecipeThrowsIllegalArgumentException() {
        RecipeServices recipeServices = new RecipeServices();
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Recipe newRecipe = new Recipe(ingredients, "steps");
        newRecipe.setId(1);
        recipeServices.save(newRecipe);
        recipeServices.deleteRecipe(1, -1);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteRecipeThrowsResourceNotFoundException()  {
        RecipeServices recipeServices = new RecipeServices();
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Recipe newRecipe = new Recipe(ingredients, "steps");
        newRecipe.setId(1);
        recipeServices.save(newRecipe);
        recipeServices.deleteRecipe(1, 5);
    }

    @Test
    public void findRecipeById() {
        RecipeServices recipeServices = new RecipeServices();
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Recipe newRecipe = new Recipe(ingredients, "steps");
        newRecipe.setId(1);
        newRecipe.setUserId(2);
        recipeServices.save(newRecipe);
        Recipe foundedRecipe = recipeServices.getRecipeById(1);
        Assert.assertTrue(foundedRecipe.equals(newRecipe));
    }

    @Test(expected = IllegalArgumentException.class)
    public void findRecipeByIdThrowsIllegalArgumentException() {
        RecipeServices recipeServices = new RecipeServices();
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Recipe newRecipe = new Recipe(ingredients, "steps");
        newRecipe.setId(1);
        recipeServices.save(newRecipe);
        recipeServices.getRecipeById(-1);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findRecipeByIdThrowsResourceNotFoundException()  {
        RecipeServices recipeServices = new RecipeServices();
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Recipe newRecipe = new Recipe(ingredients, "steps");
        newRecipe.setId(1);
        recipeServices.save(newRecipe);
        recipeServices.getRecipeById(2);
    }
}
