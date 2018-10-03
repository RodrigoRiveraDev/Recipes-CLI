package RecipeServicesTest;

import com.recipes.Controllers.RecipeController;
import com.recipes.DTO.Ingredient;
import com.recipes.DTO.Recipe;
import com.recipes.Exceptions.ResourceNotFoundException;
import com.recipes.Exceptions.UnauthorizedException;
import com.recipes.Services.RecipeServices;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class RecipeControllerTest {

    @TestConfiguration
    static class RecipeServiceImplTestContextConfiguration {

        @Bean
        public RecipeServices recipeService() {
            return new RecipeServices();
        }

        @Bean
        public RecipeController recipeController() {
            return new RecipeController();
        }
    }

    @Autowired
    private RecipeServices recipeService;

    @Autowired
    private RecipeController recipeController;

    @Test
    public void addNewRecipe() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Recipe newRecipe = new Recipe(ingredients, "steps");
        int savedRecipes;
        HttpEntity re = recipeController.registerRecipe(newRecipe);
        Recipe postReturn = (Recipe)re.getBody();
        savedRecipes = recipeController.recipeList().size();
        Assert.assertTrue(postReturn.equals(newRecipe));
    }

    @Test
    public void addNewRecipeThrowsIllegalArgumentException()  {
        HttpEntity response = recipeController.registerRecipe(new Recipe());
        Assert.assertTrue(response.getBody().equals("All the parameters must not be nulls or empties"));
    }

    @Test
    public void updateRecipeThrowsIllegalArgumentException()  {
        HttpEntity response = recipeController.updateRecipe(4, -1, new Recipe());
        Assert.assertTrue(response.getBody().equals("Negative id is not valid"));
    }

    @Test
    public void updateRecipeThrowsResourceNotFoundException()  {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Recipe newRecipe = new Recipe(ingredients, "steps");
        HttpEntity response = recipeController.updateRecipe(4,9, newRecipe);
        Assert.assertTrue(response.getBody().equals("The Recipe with id " + 9 + " was not found"));
    }

    @Test
    public void updateRecipeThrowsUnauthorizedException() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Recipe newRecipe = new Recipe(ingredients, "steps");
        newRecipe.setId(1);
        newRecipe.setUserId(2);
        recipeController.registerRecipe(newRecipe);
        Recipe updateInfo = new Recipe(new ArrayList<Ingredient>(), "new step");
        HttpEntity response = recipeController.updateRecipe(4, 1, updateInfo);
        Assert.assertTrue(response.getBody().equals("You don't have the permission to execute this action"));
    }

    @Test
    public void updateRecipe() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Recipe newRecipe = new Recipe(ingredients, "steps");
        newRecipe.setId(1);
        newRecipe.setUserId(4);
        recipeController.registerRecipe(newRecipe);
        Recipe updateInfo = new Recipe(new ArrayList<Ingredient>(), "new step");
        Recipe updatedRecipe = (Recipe) recipeController.updateRecipe(4,1, updateInfo).getBody();
        updateInfo.setId(1);
        updateInfo.setIngredients(ingredients);
        Assert.assertTrue(updatedRecipe.equals(updateInfo));
    }

    @Test
    public void deleteRecipe() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Recipe newRecipe = new Recipe(ingredients, "steps");
        newRecipe.setId(1);
        newRecipe.setUserId(2);
        recipeController.registerRecipe(newRecipe);
        int savedRecipes = recipeController.recipeList().size();
        recipeController.deleteRecipe(2, 1);
        int postDeletionSavedRecipes = recipeController.recipeList().size();
        Assert.assertTrue(savedRecipes == postDeletionSavedRecipes+1);
    }

    @Test
    public void deleteRecipeThrowsUnauthorizedException() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Recipe newRecipe = new Recipe(ingredients, "steps");
        newRecipe.setId(1);
        newRecipe.setUserId(2);
        recipeController.registerRecipe(newRecipe);
        HttpEntity response = recipeController.deleteRecipe(1, 1);
        Assert.assertTrue(response.getBody().equals("You don't have the permission to execute this action"));
    }

    @Test
    public void deleteRecipeThrowsIllegalArgumentException() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Recipe newRecipe = new Recipe(ingredients, "steps");
        newRecipe.setId(1);
        recipeController.registerRecipe(newRecipe);
        HttpEntity response = recipeController.deleteRecipe(1, -1);
        Assert.assertTrue(response.getBody().equals("Negative id is not valid"));
    }

    @Test
    public void deleteRecipeThrowsResourceNotFoundException()  {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Recipe newRecipe = new Recipe(ingredients, "steps");
        newRecipe.setId(1);
        recipeController.registerRecipe(newRecipe);
        HttpEntity response = recipeController.deleteRecipe(1, 5);
        Assert.assertTrue(response.getBody().equals("The Recipe with id " + 5 + " was not found"));
    }

    @Test
    public void findRecipeById() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Recipe newRecipe = new Recipe(ingredients, "steps");
        newRecipe.setId(1);
        newRecipe.setUserId(2);
        recipeController.registerRecipe(newRecipe);
        HttpEntity response = recipeController.getRecipeById(1);
        Recipe foundedRecipe = (Recipe) response.getBody();
        Assert.assertTrue(foundedRecipe.equals(newRecipe));
    }

    @Test
    public void findRecipeByIdThrowsIllegalArgumentException() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Recipe newRecipe = new Recipe(ingredients, "steps");
        newRecipe.setId(1);
        recipeController.registerRecipe(newRecipe);
        HttpEntity response = recipeController.getRecipeById(-1);
        Assert.assertTrue(response.getBody().equals("Negative id is not valid"));
    }

    @Test
    public void findRecipeByIdThrowsResourceNotFoundException()  {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Recipe newRecipe = new Recipe(ingredients, "steps");
        newRecipe.setId(1);
        recipeController.registerRecipe(newRecipe);
        HttpEntity response = recipeController.getRecipeById(9);
        Assert.assertTrue(response.getBody().equals("The Recipe with id " + 9 + " was not found"));
    }
}
