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

    public String enterUserId() {
        System.out.println("Enter your id, by default will be 0");
        return reader.nextLine();
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

    public String viewAllUser() {
        return userServices.viewAllUsers();
    }
}
