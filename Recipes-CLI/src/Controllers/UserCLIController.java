package Controllers;

import java.util.Scanner;

import Handlers.HttpRequestHandler;
import Services.UserServicesCLI;
import Utilitaries.FieldValidator;

public class UserCLIController {

    private UserServicesCLI userServices;
    private Scanner reader;

    public UserCLIController(HttpRequestHandler httpRequestHandler) {
        reader = new Scanner(System.in);
        userServices = new UserServicesCLI(httpRequestHandler);
    }

    public int getInitInstructions() {
        System.out.println("1. To Register \n2. To Log In\n3. To Exit");
        return FieldValidator.mainOption(reader.nextLine());
    }

    private String register() {
        String fullName, email, password;
        System.out.println("full name: ");
        fullName = reader.nextLine();
        System.out.println("email: ");
        email = reader.nextLine();
        System.out.println("password: ");
        password = reader.nextLine();
        return userServices.registerUser(fullName, email, password);
    }

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

    public void selectOption(int value) {
        switch (value) {
            case 1 : {
                register();
                break;
            }
        }
    }

}
