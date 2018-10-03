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
}
