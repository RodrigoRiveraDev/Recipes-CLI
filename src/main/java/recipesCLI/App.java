package recipesCLI;

import org.codehaus.jackson.map.ObjectMapper;
import recipesCLI.Controllers.ApplicationController;
import recipesCLI.DTO.IngredientDTO;
import recipesCLI.HttpRequestSender.HttpRequestSender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        /*HttpRequestSender httpRequestSender = new HttpRequestSender();

        ApplicationController applicationController = new ApplicationController(httpRequestSender);

        applicationController.enterUserId();

        int option = applicationController.getInitInstructions();
        while(option != 9) {
            System.out.println(applicationController.selectOption(option));
            option = applicationController.getInitInstructions();
        }*/

        ObjectMapper mapper = new ObjectMapper();
        List<IngredientDTO> listOfDtos = new ArrayList<>();
        listOfDtos.add(new IngredientDTO("ingredient1", 14, "gr"));
        listOfDtos.add(new IngredientDTO("ingredient2", 24, "gr"));

        try {
            //String json = new IngredientDTO("ingredient1", 14, "gr").toJSON();
            String jsonString = mapper.writeValueAsString(new IngredientDTO("ingredient2", 24, "gr"));
            //IngredientDTO fromJson = new ObjectMapper().readerForUpdating(IngredientDTO.class).readValue(json);
            System.out.println(jsonString);
            //System.out.println(fromJson.getName());

            /**String jsonArray = mapper.writeValueAsString(listOfDtos);
            List<IngredientDTO> asList = mapper.readValue(jsonArray, List.class);
            for(IngredientDTO ingredientDTO : asList) {
                System.out.println(ingredientDTO.getName());
            }*/
        } catch (IOException ex) {

        }



    }
}
