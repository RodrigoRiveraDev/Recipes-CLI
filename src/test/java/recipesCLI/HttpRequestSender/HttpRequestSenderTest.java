package recipesCLI.HttpRequestSender;

import com.sun.jersey.api.client.ClientHandlerException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import recipesCLI.CustomExceptions.BadResponseException;
import recipesCLI.CustomExceptions.CustomConnectionException;
import recipesCLI.DTO.UserDTO;

import java.net.ConnectException;

import static org.junit.Assert.*;

public class HttpRequestSenderTest {

    private HttpRequestSender httpRequestSender;

    @Before
    public void setUp() {
        httpRequestSender = new HttpRequestSender();
    }


    @Test(expected = ClientHandlerException.class)
    public void sendGetConnectException() throws ConnectException, BadResponseException, CustomConnectionException {
        httpRequestSender.sendGet("/users", null, null);
    }

    @Test(expected = ClientHandlerException.class)
    public void sendPostConnectException() throws ConnectException, BadResponseException, CustomConnectionException {
        UserDTO userDTO = new UserDTO();
        httpRequestSender.sendPost("/users", userDTO, null);
    }

    @Test(expected = BadResponseException.class)
    public void sendPostBadResponseException() throws ConnectException, BadResponseException, CustomConnectionException {
        UserDTO userDTO = new UserDTO();
        httpRequestSender.sendPost("/users", userDTO, null);
    }

    @Test
    public void sendPost() throws ConnectException, BadResponseException, CustomConnectionException {
        UserDTO userDTO = new UserDTO("fullName", "email", "password");
        String response = httpRequestSender.sendPost("/users", userDTO, null);
        String userDTOString = userDTO.toString();
        Assert.assertTrue(response.contains(userDTOString));
    }

    @Test(expected = ConnectException.class)
    public void sendPut() {
    }

    @Test(expected = ConnectException.class)
    public void sendDelete() {
    }
}