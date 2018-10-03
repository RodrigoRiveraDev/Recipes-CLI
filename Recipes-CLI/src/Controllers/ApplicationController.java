package Controllers;

import Handlers.HttpRequestHandler;
import Utilitaries.FieldValidator;

import java.util.Scanner;

public class ApplicationController {

    private RecipeController recipeController;
    private UserCLIController userCLIController;
    private Scanner reader;
    private int userId;

    public ApplicationController(HttpRequestHandler httpRequestHandler) {
        recipeController = new RecipeController(httpRequestHandler);
        userCLIController = new UserCLIController(httpRequestHandler);
        reader = new Scanner(System.in);
    }

    public void enterUserId() {
        System.out.println("Enter your id, by default will be 0");
        userId = Integer.parseInt(reader.nextLine());
    }

    public int getInitInstructions() {
        System.out.println( "1. To Register new User\n"+
                "2. List all users\n"+
                "3. Modify personal data\n"+
                "4. Add a recipes\n"+
                "5. List all recipes\n"+
                "6. Find Recipe by id\n"+
                "7. Modify a recipe\n"+
                "8. Delete a recipe\n"+
                "9. Finish the app\n");
        return FieldValidator.mainOption(reader.nextLine());
    }

    public String selectOption(int value) {
        switch (value) {
            case 1 : {
                return userCLIController.register();
            }
            case 2: {
                return userCLIController.viewAllUser();
            }
            case 3: {
                return userCLIController.update(userId);
            }
            case 4: {
                return recipeController.registerRecipe(userId);
            }
            case 5: {
                return recipeController.getAllRecipes();
            }
            case 6: {
                return recipeController.getRecipeById();
            }
            case 7: {
                return recipeController.modifyRecipe(userId);
            }
            case 8: {
                return recipeController.deleteRecipe(userId);
            }
        }
        return "See you soon";
    }
}
