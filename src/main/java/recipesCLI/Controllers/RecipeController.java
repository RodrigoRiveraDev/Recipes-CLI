package recipesCLI.Controllers;

import recipesCLI.HttpRequestSender.HttpRequestSender;
import recipesCLI.Services.RecipeServicesCLI;

import java.util.Scanner;

public class RecipeController {

    private RecipeServicesCLI recipeServices;
    private Scanner reader;

    public RecipeController(HttpRequestSender httpRequestSender) {
        reader = new Scanner(System.in);
        recipeServices = new RecipeServicesCLI(httpRequestSender);
    }

    /**
     * This method is to add ingredients to the current recipe
     */
    private void addIngredient() {
        String name, quantity, unit;
        System.out.println("name: ");
        name = reader.nextLine();
        System.out.println("quantity: ");
        quantity = reader.nextLine();
        System.out.println("unit: ");
        unit = reader.nextLine();
        recipeServices.addIngredient(name, quantity, unit);
    }

    /**
     * This function is to register a new Recipe
     * @param currentId the user id that is registering the new Recipe
     * @return This will return a String with created Recipe as a Json
     */
    public String registerRecipe(int currentId) {
        recipeServices.startRegisteringRecipe();
        String howElaborate;
        System.out.println("how Elaborate: ");
        howElaborate = reader.nextLine();
        System.out.println("Add your ingredients:");
        addIngredient();
        System.out.println("Want to add another ingredient? [Y/N]");
        String keepAdding = reader.nextLine();
        while(keepAdding.equals("Y")) {
            addIngredient();
            System.out.println("Want to add another ingredient? [Y/N]");
            keepAdding = reader.nextLine();
        }
        return recipeServices.finishRegisterRecipe(howElaborate, currentId);
    }

    /**
     * @return This will return a String with all the registered Recipes as Json format or a error message
     */
    public String getAllRecipes() {
        return recipeServices.getAllRecipes();
    }

    /**
     * The id is read from the CLI
     * @return Tis will return a Recipe by its id
     */
    public String getRecipeById() {
        String id;
        System.out.println("recipe id: ");
        id = reader.nextLine();
        return recipeServices.getRecipeById(id);
    }

    /**
     * @param currentId The user id that request the change
     * @return It will return the updated Recipe as a String in Json format or a error message
     */
    public String modifyRecipe(int currentId) {
        recipeServices.startRegisteringRecipe();
        String howElaborate, recipeId;
        System.out.println("how Elaborate: ");
        howElaborate = reader.nextLine();
        System.out.println("recipe Id: ");
        recipeId = reader.nextLine();
        System.out.println("Add your ingredients:");
        System.out.println("Want to modify ingredient? [Y/N]");
        String keepAdding = reader.nextLine();
        while(keepAdding.equals("Y")) {
            addIngredient();
            System.out.println("Want to add another ingredient? [Y/N]");
            keepAdding = reader.nextLine();
        }
        return recipeServices.finishUpdateRecipe(howElaborate, recipeId, currentId);
    }

    /**
     * @param currentId The user id that request the deletion
     * @return A successful message or an error message
     */
    public String deleteRecipe(int currentId) {
        String id;
        System.out.println("recipe id: ");
        id = reader.nextLine();
        return recipeServices.deleteRecipe(id, currentId);
    }
}
