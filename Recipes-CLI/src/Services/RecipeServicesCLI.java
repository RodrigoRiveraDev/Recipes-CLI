package Services;

import DTO.IngredientDTO;
import DTO.RecipeDTO;
import Handlers.HttpRequestHandler;
import Utilitaries.IngredientFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeServicesCLI {
    private HttpRequestHandler httpRequestHandler;
    private RecipeDTO recipeDTO;

    public RecipeServicesCLI (HttpRequestHandler httpRequestHandler) {
        this.httpRequestHandler = httpRequestHandler;
    }

    public void startRegisteringRecipe() {
        recipeDTO = new RecipeDTO();
    }

    public void addIngredient(String name, String quantity, String unit) {
        List<IngredientDTO> ingredients = recipeDTO.getIngredients();
        IngredientDTO ingredient = IngredientFactory.createIngredient(name, quantity, unit);
        ingredients.add(ingredient);
        recipeDTO.setIngredients(ingredients);
    }

    public String finishRegisterRecipe(String howElaborate, int userId) {
        recipeDTO.setHowElaborate(howElaborate);
        recipeDTO.setUserId(userId);
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("userId", Integer.toString(userId));
            return httpRequestHandler.sendPost("/recipes", recipeDTO, headers);
        } catch (Exception ex) {
            return "There was an error, try again";
        }
    }

    public String finishUpdateRecipe(String howElaborate, String recipeId, int userId) {
        recipeDTO.setHowElaborate(howElaborate);
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("userId", Integer.toString(userId));
            return httpRequestHandler.sendPut("/recipes/"+recipeId, recipeDTO, headers);
        } catch (Exception ex) {
            return "There was an error, try again";
        }
    }

    public String getAllRecipes() {
        try {
            return this.httpRequestHandler.sendGet("/recipes", null, null);
        } catch (Exception ex) {
            return "There was an error, try again";
        }
    }

    public String getRecipeById(String id) {
        try {
            return this.httpRequestHandler.sendGet("/recipes/"+id, null, null);
        } catch (Exception ex) {
            return "There was an error, try again";
        }
    }

    public String deleteRecipe(String id, int userId) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("userId", Integer.toString(userId));
            return this.httpRequestHandler.sendDelete("/recipes/"+id, null, headers);
        } catch (Exception ex) {
            return "There was an error, try again";
        }
    }
}
