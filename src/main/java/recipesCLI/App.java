package recipesCLI;

import recipesCLI.Controllers.ApplicationController;
import recipesCLI.Handlers.HttpRequestHandler;

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
    }
}
