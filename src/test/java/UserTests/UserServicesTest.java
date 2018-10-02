package UserTests;

import com.recipes.DTO.User;

import com.recipes.Services.UserServices;
import org.junit.Assert;
import org.junit.Test;


public class UserServicesTest {

    @Test
    public void addNewUser() {
        UserServices userServices = new UserServices();
        User newUser = new User();
        int savedUsers;
        userServices.save(newUser);
        savedUsers = userServices.getUserList().size();
        Assert.assertTrue(savedUsers == 1);
    }

}
