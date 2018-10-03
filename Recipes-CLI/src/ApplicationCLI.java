import Controllers.UserCLIController;
import DTO.UserDTO;
import Handlers.HttpRequestHandler;

public class ApplicationCLI {
    public static void main(String[] args) {
        HttpRequestHandler httpRequestHandler = new HttpRequestHandler();

        UserCLIController userCLIController = new UserCLIController(httpRequestHandler);

        int option = userCLIController.getInitInstructions();
        while(option != 3) {
            userCLIController.selectOption(option);
            option = userCLIController.getInitInstructions();
        }




    }
}
