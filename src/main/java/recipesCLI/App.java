package recipesCLI;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import recipesCLI.Controllers.ApplicationController;
import recipesCLI.Controllers.RecipeController;
import recipesCLI.DTO.IJSON;
import recipesCLI.DTO.IngredientDTO;
import recipesCLI.DTO.RecipeDTO;
import recipesCLI.DTO.UserDTO;
import recipesCLI.Handlers.HttpRequestHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        HttpRequestHandler httpRequestHandler = new HttpRequestHandler();

        ApplicationController applicationController = new ApplicationController(httpRequestHandler);

        applicationController.enterUserId();

        int option = applicationController.getInitInstructions();
        while(option != 9) {
            System.out.println(applicationController.selectOption(option));
            option = applicationController.getInitInstructions();
        }


        /*List<IngredientDTO> ingredientDTOList = new ArrayList<>();
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setName("sugar");
        ingredientDTO.setQuantity(12);
        ingredientDTO.setUnit("gr");
        ingredientDTOList.add(ingredientDTO);
        RecipeDTO recipe = new RecipeDTO(ingredientDTOList, "stetps");

        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("userId", "1");
            System.out.println(httpRequestHandler.sendPost("/recipes", recipe, headers));
        } catch(Exception ex) {

        }*/
    }
}
