package recipesCLI.Services;

import recipes.sharedDomain.DTO.UserDTO;
import recipesCLI.Utilitaries.UserFactory;
import recipesCLI.HttpRequestSender.HttpRequestSender;

import java.util.HashMap;
import java.util.Map;

public class UserServicesCLI {
    private HttpRequestSender httpRequestSender;

    public UserServicesCLI (HttpRequestSender httpRequestSender) {
        this.httpRequestSender = httpRequestSender;
    }

    /**
     * @param fullName The user fullName
     * @param email The user email
     * @param password The user password
     * @return It will return the new user as a String with Json format or an error message
     */
    public String registerUser(String fullName, String email, String password) {
        UserDTO user = UserFactory.createUser(fullName, email, password);
        try {
            return this.httpRequestSender.sendPost("/users", user, null);
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     * @param currentId The user id that request the update
     * @param fullName The fullName value
     * @param email The email value
     * @param password The password value
     * @return It will return the updated user as a string with Json format or am error message
     */
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

    /**
     * @return It will return a list as a String with Json format with all the registered Users
     */
    public String viewAllUsers() {
        try {
            return this.httpRequestSender.sendGet("/users", null, null);
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
