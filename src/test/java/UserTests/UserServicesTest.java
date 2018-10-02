package UserTests;

import com.recipes.DTO.User;

import com.recipes.Exceptions.ResourceNotFoundException;
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

    @Test(expected = IllegalArgumentException.class)
    public void findUserbyIdThrowsIllegalArgumentException()  {
        UserServices userServices = new UserServices();
        userServices.findUserbyId(-1);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findUserbyIdThrowsResourceNotFoundException()  {
        UserServices userServices = new UserServices();
        userServices.findUserbyId(0);
    }

    @Test
    public void findUserById() {
        UserServices userServices = new UserServices();
        User newUser = new User(1, "fullName", "email", "password");
        userServices.save(newUser);
        User foundedUser = userServices.findUserbyId(1);
        Assert.assertTrue(foundedUser.equals(newUser));
    }
}
