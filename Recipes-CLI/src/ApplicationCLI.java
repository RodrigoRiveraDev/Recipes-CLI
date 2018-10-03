import Controllers.RecipeController;
import Controllers.UserCLIController;
import DTO.IJSON;
import DTO.UserDTO;
import Handlers.HttpRequestHandler;

public class ApplicationCLI {
    public static void main(String[] args) {
        HttpRequestHandler httpRequestHandler = new HttpRequestHandler();

        UserCLIController userCLIController = new UserCLIController(httpRequestHandler);
        RecipeController recipeController = new RecipeController(httpRequestHandler);

        int currentId = 0;
        int option = userCLIController.getInitInstructions();
        while(option != 3) {
            userCLIController.selectOption(option);
            option = userCLIController.getInitInstructions();
        }
    }
}
