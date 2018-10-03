package Services;

import DTO.UserDTO;
import Utilitaries.UserFactory;
import Handlers.HttpRequestHandler;

public class UserServicesCLI {
    private HttpRequestHandler connector;
    public UserServicesCLI (HttpRequestHandler connector) {
        this.connector = connector;
    }

    public String registerUser(String fullName, String email, String password) {
        UserDTO user = UserFactory.createUser(fullName, email, password);
        try {
            return this.connector.sendPost("/users", user, null);
        } catch (Exception ex) {
            return "There was an error, try again";
        }
    }
}
