package Services;

import DTO.UserDTO;
import Utilitaries.UserFactory;
import Handlers.HttpRequestHandler;

import java.util.HashMap;
import java.util.Map;

public class UserServicesCLI {
    private HttpRequestHandler httpRequestHandler;

    public UserServicesCLI (HttpRequestHandler httpRequestHandler) {
        this.httpRequestHandler = httpRequestHandler;
    }

    public String registerUser(String fullName, String email, String password) {
        UserDTO user = UserFactory.createUser(fullName, email, password);
        try {
            return this.httpRequestHandler.sendPost("/users", user, null);
        } catch (Exception ex) {
            return "There was an error, try again";
        }
    }

    public String updateUser(int currentId, String fullName, String email, String password) {
        UserDTO user = UserFactory.createUser(fullName, email, password);
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("userId", Integer.toString(currentId));
            return this.httpRequestHandler.sendPut("/users/"+currentId, user, headers);
        } catch (Exception ex) {
            return "There was an error, try again";
        }
    }

    public String viewAllUsers() {
        try {
            return this.httpRequestHandler.sendGet("/users", null, null);
        } catch (Exception ex) {
            return "There was an error, try again";
        }
    }

}
