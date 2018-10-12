package recipesCLI;

import recipesCLI.Controllers.ApplicationController;
import recipesCLI.HttpRequestSender.HttpRequestSender;

public class App 
{
    public static void main( String[] args )
    {
        HttpRequestSender httpRequestSender = new HttpRequestSender();

        ApplicationController applicationController = new ApplicationController(httpRequestSender);

        applicationController.enterUserId();

        int option = applicationController.getInitInstructions();
        while(option != 9) {
            System.out.println(applicationController.selectOption(option));
            option = applicationController.getInitInstructions();
        }
    }
}
