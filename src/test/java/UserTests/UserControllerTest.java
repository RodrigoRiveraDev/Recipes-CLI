package UserTests;

import com.recipes.Controllers.UserController;
import com.recipes.DTO.User;
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
        userController.registerUser(newUser);
        int savedUsers;
        savedUsers = userController.userList().size();
        Assert.assertTrue(savedUsers == 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNewUserThrowsExeption()  {
        User newUser = new User();
        userController.registerUser(newUser);
    }

    @Test
    public void findUserbyId()  {
        User newUser = new User(1, "fullName", "email", "password");
        userController.registerUser(newUser);
        HttpEntity<User> response = userController.getUserById(1);
        User foundedUser = response.getBody();
        Assert.assertTrue(foundedUser.equals(newUser));
    }
}
