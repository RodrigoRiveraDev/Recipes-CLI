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

    /**
     * @param name The ingredient name
     * @param quantity The ingredient quantity
     * @param unit The ingredient unit
     */
    public void addIngredient(String name, String quantity, String unit) {
        List<IngredientDTO> ingredients = recipeDTO.getIngredients();
        IngredientDTO ingredient = IngredientFactory.createIngredient(name, quantity, unit);
        ingredients.add(ingredient);
        recipeDTO.setIngredients(ingredients);
    }

    /**
     * @param howElaborate The howElaborate value
     * @param userId The userId that requests the registration
     * @return It will return the Recipe as a String with Json format or an error message
     */
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

    /**
     * @param howElaborate The howElaborate value
     * @param userId The userId that requests the update
     * @return It will return the Recipe as a String with Json format or an error message
     */
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

    /**
     * @return It will retrieve a list in Json format with all the registered Recipes
     */
    public String getAllRecipes() {
        try {
            return this.httpRequestSender.sendGet("/recipes", null, null);
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     * @param id The Recipe id to search for
     * @return It will return a Recipe as a String with Json format or an error message
     */
    public String getRecipeById(String id) {
        try {
            return this.httpRequestSender.sendGet("/recipes/"+id, null, null);
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     * @param id The recipe id to delete
     * @param userId The user id that request the deletion
     * @return It will return an successfully deleted or an error message
     */
    public String deleteRecipe(String id, int userId) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("userId", Integer.toString(userId));
            this.httpRequestSender.sendDelete("/recipes/"+id, null, headers);
            return "Recipe with id: " + id + "successfully deleted";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
