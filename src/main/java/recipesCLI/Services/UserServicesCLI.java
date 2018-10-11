package recipesCLI.Services;

import recipesCLI.DTO.UserDTO;
import recipesCLI.Utilitaries.UserFactory;
import recipesCLI.HttpRequestSender.HttpRequestSender;

import java.util.HashMap;
import java.util.Map;

public class UserServicesCLI {
    private HttpRequestSender httpRequestSender;

    public UserServicesCLI (HttpRequestSender httpRequestSender) {
        this.httpRequestSender = httpRequestSender;
    }

    public String registerUser(String fullName, String email, String password) {
        UserDTO user = UserFactory.createUser(fullName, email, password);
        try {
            return this.httpRequestSender.sendPost("/users", user, null);
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public String updateUser(int currentId, String fullName, String email, String password) {
        UserDTO user = UserFactory.createUser(fullName, email, password);
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("userId", Integer.toString(currentId));
            return this.httpRequestSender.sendPut("/users/"+currentId, user, headers);
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public String viewAllUsers() {
        try {
            return this.httpRequestSender.sendGet("/users", null, null);
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

}
