package recipesCLI.Services;

import recipesCLI.DTO.IngredientDTO;
import recipesCLI.DTO.RecipeDTO;
import recipesCLI.HttpRequestSender.HttpRequestSender;
import recipesCLI.Utilitaries.IngredientFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeServicesCLI {
    private HttpRequestSender httpRequestSender;
    private RecipeDTO recipeDTO;

    public RecipeServicesCLI (HttpRequestSender httpRequestSender) {
        this.httpRequestSender = httpRequestSender;
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
            return httpRequestSender.sendPost("/recipes", recipeDTO, headers);
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public String finishUpdateRecipe(String howElaborate, String recipeId, int userId) {
        recipeDTO.setHowElaborate(howElaborate);
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("userId", Integer.toString(userId));
            return httpRequestSender.sendPut("/recipes/"+recipeId, recipeDTO, headers);
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public String getAllRecipes() {
        try {
            return this.httpRequestSender.sendGet("/recipes", null, null);
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public String getRecipeById(String id) {
        try {
            return this.httpRequestSender.sendGet("/recipes/"+id, null, null);
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public String deleteRecipe(String id, int userId) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("userId", Integer.toString(userId));
            return this.httpRequestSender.sendDelete("/recipes/"+id, null, headers);
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
