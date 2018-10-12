package recipesCLI.Controllers;

import java.util.Scanner;

import recipesCLI.HttpRequestSender.HttpRequestSender;
import recipesCLI.Services.UserServicesCLI;

public class UserCLIController {

    private UserServicesCLI userServices;
    private Scanner reader;

    public UserCLIController(HttpRequestSender httpRequestSender) {
        reader = new Scanner(System.in);
        userServices = new UserServicesCLI(httpRequestSender);
    }

    /**
     * @return It will return the new registered user as a String in Json format or a error message
     */
    public String register() {
        String fullName, email, password;
        System.out.println("full name: ");
        fullName = reader.nextLine();
        System.out.println("email: ");
        email = reader.nextLine();
        System.out.println("password: ");
        password = reader.nextLine();
        return userServices.registerUser(fullName, email, password);
    }

    /**
     * @param currentId The user id that request the update
     * @return It will return the updated user as a string with Json format or a error message
     */
    public String update(int currentId) {
        String fullName, email, password;
        System.out.println("full name: ");
        fullName = reader.nextLine();
        System.out.println("email: ");
        email = reader.nextLine();
        System.out.println("password: ");
        password = reader.nextLine();
        return userServices.updateUser(currentId, fullName, email, password);
    }

    /**
     * @return It will return all the registered users as a String with Json format
     */
    public String viewAllUser() {
        return userServices.viewAllUsers();
    }
}
