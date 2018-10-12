package recipesCLI.Services;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import recipesCLI.HttpRequestSender.HttpRequestSender;

import static org.junit.Assert.*;

public class UserServicesCLITest {

    private UserServicesCLI userServicesCLI;

    @Before
    public void setUp() {
        userServicesCLI = new UserServicesCLI(new HttpRequestSender());
    }

    @Test
    public void registerUser() {
        String response = userServicesCLI.registerUser("fullNameCLI", "emailCLI",
                "passwordCLI");
        assertThat(response, CoreMatchers.containsString("fullNameCLI"));
        assertThat(response, CoreMatchers.containsString("emailCLI"));
        assertThat(response, CoreMatchers.containsString("passwordCLI"));
    }

    @Test
    public void registerUserErrorResponse() {
        String response = userServicesCLI.registerUser("fullNameCLI", "emailCLI",
                "passwordCLI");
        assertThat(response, CoreMatchers.containsString("error"));
    }

    @Test
    public void updateUser() {
        userServicesCLI.registerUser("fullNameCLIUpdate", "emailCLIUpdate",
                "passwordCLIUpdate");
        String response = userServicesCLI.updateUser(1, "NewName", "", "");
        assertThat(response, CoreMatchers.containsString("NewName"));
    }

    @Test
    public void updateUserErrorResponse() {
        String response = userServicesCLI.updateUser(100, "NewName", "", "");
        assertThat(response, CoreMatchers.containsString("error"));
    }

    @Test
    public void viewAllUsers() {
        String response = userServicesCLI.viewAllUsers();
        assertThat(response, CoreMatchers.containsString("["));
        assertThat(response, CoreMatchers.containsString("]"));
    }
}