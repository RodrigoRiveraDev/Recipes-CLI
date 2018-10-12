package recipesCLI.Services;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import recipes.sharedDomain.DTO.UserDTO;

import static org.mockito.Mockito.mock;

import static org.junit.Assert.*;

public class UserServicesCLITest {

    private UserServicesCLI userServicesCLI;
    private UserDTO dummyUser;

    @Before
    public void setUp() {
        userServicesCLI = mock(UserServicesCLI.class);
        dummyUser = new UserDTO("dummy", "a@a.com", "password");
    }

    @Test
    public void registerUser() {
        Mockito.when(userServicesCLI.registerUser(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString())).thenReturn(dummyUser.toJSON());

        String response = userServicesCLI.registerUser("dummy", "a@a.com",
                "password");
        assertThat(response, CoreMatchers.containsString(dummyUser.getFullName()));
        assertThat(response, CoreMatchers.containsString(dummyUser.getEmail()));
        assertThat(response, CoreMatchers.containsString(dummyUser.getPassword()));
    }

    @Test
    public void registerUserErrorResponse() {
        Mockito.when(userServicesCLI.registerUser(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString())).thenReturn("error");
        String response = userServicesCLI.registerUser("fullNameCLI", "emailCLI",
                "passwordCLI");
        assertThat(response, CoreMatchers.containsString("error"));
    }

    @Test
    public void updateUser() {
        Mockito.when(userServicesCLI.updateUser(Mockito.anyInt(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString())).thenReturn(dummyUser.toJSON());

        userServicesCLI.updateUser(1, "dummy", "a@a.com", "pass");
        String response = userServicesCLI.updateUser(1, "NewName", "", "");
        assertThat(response, CoreMatchers.containsString("dummy"));
    }

    @Test
    public void updateUserErrorResponse() {
        Mockito.when(userServicesCLI.updateUser(Mockito.anyInt(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString())).thenReturn("error");
        String response = userServicesCLI.updateUser(100, "NewName", "", "");
        assertThat(response, CoreMatchers.containsString("error"));
    }

    @Test
    public void viewAllUsers() {
        Mockito.when(userServicesCLI.viewAllUsers()).thenReturn("[]");
        String response = userServicesCLI.viewAllUsers();
        assertThat(response, CoreMatchers.containsString("["));
        assertThat(response, CoreMatchers.containsString("]"));
    }
}