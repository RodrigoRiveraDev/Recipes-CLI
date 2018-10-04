package Controllers;

import Handlers.HttpRequestHandler;
import Services.RecipeServicesCLI;

import java.util.Scanner;

public class RecipeController {

    private RecipeServicesCLI recipeServices;
    private Scanner reader;

    public RecipeController(HttpRequestHandler httpRequestHandler) {
        reader = new Scanner(System.in);
        recipeServices = new RecipeServicesCLI(httpRequestHandler);
    }

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

    public String registerRecipe(int currentId) {
        recipeServices.startRegisteringRecipe();
        String howElaborate;
        System.out.println("how Elaborate: ");
        howElaborate = reader.nextLine();
        System.out.println("Add your ingredients:");
        addIngredient();
        System.out.println("Want to add another ingredient? [Y/N]");
        String keedAdding = reader.nextLine();
        while(keedAdding.equals("Y")) {
            addIngredient();
            System.out.println("Want to add another ingredient? [Y/N]");
            keedAdding = reader.nextLine();
        }
        return recipeServices.finishRegisterRecipe(howElaborate, currentId);
    }

    public String getAllRecipes() {
        return recipeServices.getAllRecipes();
    }

    public String getRecipeById() {
        String id;
        System.out.println("recipe id: ");
        id = reader.nextLine();
        return recipeServices.getRecipeById(id);
    }

    public String modifyRecipe(int currentId) {
        recipeServices.startRegisteringRecipe();
        String howElaborate, recipeId;
        System.out.println("how Elaborate: ");
        howElaborate = reader.nextLine();
        System.out.println("recipe Id: ");
        recipeId = reader.nextLine();
        System.out.println("Add your ingredients:");
        System.out.println("Want to modify ingredient? [Y/N]");
        String keedAdding = reader.nextLine();
        while(keedAdding.equals("Y")) {
            addIngredient();
            System.out.println("Want to add another ingredient? [Y/N]");
            keedAdding = reader.nextLine();
        }
        return recipeServices.finishUpdateRecipe(howElaborate, recipeId, currentId);
    }

    public String deleteRecipe(int currentId) {
        String id;
        System.out.println("recipe id: ");
        id = reader.nextLine();
        return recipeServices.deleteRecipe(id, currentId);
    }
}
