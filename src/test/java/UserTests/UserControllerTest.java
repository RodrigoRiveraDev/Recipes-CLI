package UserTests;

import com.recipes.Controllers.UserController;
import com.recipes.DTO.User;
import com.recipes.Exceptions.ResourceNotFoundException;
import com.recipes.Exceptions.UnauthorizedException;
import com.recipes.Services.UserServices;
import com.sun.deploy.net.HttpResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserControllerTest {

    @TestConfiguration
    static class UsersServiceImplTestContextConfiguration {

        @Bean
        public UserServices usersService() {
            return new UserServices();
        }

        @Bean
        public UserController userController() {
            return new UserController();
        }
    }

    @Autowired
    private UserServices usersService;

    @Autowired
    private UserController userController;

    @Test
    public void addNewUser() {
        User newUser = new User(1, "fullName", "email", "password");
        int originalSavedUsers = userController.userList().size();
        userController.registerUser(newUser);
        int newSavedUsers = userController.userList().size();
        Assert.assertTrue(newSavedUsers == originalSavedUsers + 1);
    }

    @Test
    public void addNewUserThrowsException()  {
        User newUser = new User();
        HttpEntity response = userController.registerUser(newUser);
        Assert.assertTrue(response.getBody().equals("All the parameters must not be nulls or empties"));
    }

    @Test
    public void findUserbyId()  {
        User newUser = new User(1, "fullName", "email", "password");
        userController.registerUser(newUser);
        HttpEntity<User> response = userController.getUserById(1);
        User foundedUser = response.getBody();
        Assert.assertTrue(foundedUser.toString().equals(newUser.toString()));
    }

    @Test
    public void findUserbyIdThrowsIllegalArgumentException()  {
        HttpEntity response = userController.getUserById(-1);
        Assert.assertTrue(response.getBody().equals("Negative id is not valid"));
    }

    @Test
    public void findUserbyIdThrowsResourceNotFoundException()  {
        HttpEntity response = userController.getUserById(0);
        Assert.assertTrue(response.getBody().equals("The User with id " + 0 + " was not found"));
    }

    @Test
    public void updateUser()  {
        User newUser = new User(1, "fullName", "email", "password");
        userController.registerUser(newUser);
        User updateInfo = new User(1, "NewfullName", "Newemail", "password");
        HttpEntity<User> response = userController.updateUser(1,1, updateInfo);
        User updatedUser = response.getBody();
        Assert.assertTrue(updatedUser.toString().equals(updateInfo.toString()));
    }

    @Test
    public void updateUserThrowsIllegalArgumentException()  {
        HttpEntity response = userController.updateUser(1, -1, new User());
        Assert.assertTrue(response.getBody().equals("Negative id is not valid"));
    }

    @Test
    public void updateUserThrowsResourceNotFoundException()  {
        HttpEntity response = userController.updateUser(1, 0, new User());
        Assert.assertTrue(response.getBody().equals("The User with id " + 0 + " was not found"));
    }

    @Test
    public void updateUserThrowsUnauthorizedException() {
        User newUser = new User(1, "fullName", "email", "password");
        userController.registerUser(newUser);
        User updateInfo = new User(1, "NewfullName", "Newemail", "password");
        HttpEntity response = userController.updateUser(3,1, updateInfo);
        Assert.assertTrue(response.getBody().equals("You don't have the permission to execute this action"));
    }
}
