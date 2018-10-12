package recipesCLI.HttpRequestSender;

import static org.mockito.Mockito.*;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import recipes.sharedDomain.DTO.IJSON;
import recipes.sharedDomain.DTO.UserDTO;
import recipesCLI.CustomExceptions.BadResponseException;
import recipesCLI.CustomExceptions.CustomConnectionException;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertThat;

public class HttpRequestSenderTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private HttpRequestSender httpRequestSender;

    @Before
    public void setUp() {
        httpRequestSender = mock(HttpRequestSender.class);
    }

    @Test
    public void sendGet() throws BadResponseException, CustomConnectionException {
        Mockito.when(httpRequestSender.sendGet(Mockito.anyString(), Mockito.anyMap(), Mockito.anyMap()))
                .thenReturn("[]");

        String response = httpRequestSender.sendGet("/users", null, null);
        assertThat(response, CoreMatchers.containsString("["));
        assertThat(response, CoreMatchers.containsString("]"));
    }

    @Test
    public void sendPost() throws BadResponseException, CustomConnectionException {
        UserDTO userDTO = new UserDTO("fullName", "email", "password");
        Mockito.when(httpRequestSender.sendPost(Mockito.anyString(), Mockito.any(IJSON.class),
                Mockito.anyMapOf(String.class, String.class))).thenReturn(userDTO.toJSON());

        String response = httpRequestSender.sendPost("/users", userDTO, null);
        assertThat(response, CoreMatchers.containsString(userDTO.getFullName()));
        assertThat(response, CoreMatchers.containsString(userDTO.getEmail()));
        assertThat(response, CoreMatchers.containsString(userDTO.getPassword()));
    }

    @Test
    public void sendPostThrownBadResponseException()
            throws BadResponseException, CustomConnectionException {
        UserDTO userDTO = new UserDTO("fullName", "email", "password");
        httpRequestSender.sendPost("/users", userDTO, null);
        Mockito.when(httpRequestSender.sendPost(Mockito.anyString(), Mockito.any(IJSON.class),
                Mockito.anyMapOf(String.class, String.class))).thenThrow(new BadResponseException("error"));

        thrown.expect(BadResponseException.class);
        httpRequestSender.sendPost("/users", userDTO, null);
    }

    @Test
    public void sendPut() throws CustomConnectionException, BadResponseException {
        UserDTO userDTO = new UserDTO("NewFullName", "emailPut", "passwordPut");
        Mockito.when(httpRequestSender.sendPut(Mockito.anyString(), Mockito.any(IJSON.class),
                Mockito.anyMapOf(String.class, String.class))).thenReturn(userDTO.toJSON());

        Map<String, String> headers = Collections.singletonMap("userId", "1");
        String response = httpRequestSender.sendPut("/users/1", userDTO, headers);
        assertThat(response, CoreMatchers.containsString(userDTO.getFullName()));
    }

    @Test
    public void sendPutBadResponseException() throws CustomConnectionException, BadResponseException {
        UserDTO userDTO = new UserDTO("fullNamePut2", "emailPut2", "passwordPut2");
        Mockito.when(httpRequestSender.sendPut(Mockito.anyString(), Mockito.any(IJSON.class),
                Mockito.anyMapOf(String.class, String.class))).thenThrow(new BadResponseException("error"));

        Map<String, String> headers = Collections.singletonMap("userId", "1");
        thrown.expect(BadResponseException.class);
        httpRequestSender.sendPut("/users/1", userDTO, headers);
    }

    @Test
    public void sendDelete() throws BadResponseException, CustomConnectionException, IOException {
        Mockito.when(httpRequestSender.sendDelete(Mockito.anyString(), Mockito.any(IJSON.class),
                Mockito.anyMapOf(String.class, String.class))).thenReturn("Successfully deleted");

        Map<String, String> headers = Collections.singletonMap("userId", "1");
        String response = httpRequestSender.sendDelete("/recipes/1", null, headers);
        assertThat(response, CoreMatchers.containsString("Successfully deleted"));
    }

    @Test
    public void sendDeleteBadResponseException() throws BadResponseException, CustomConnectionException {
        Mockito.when(httpRequestSender.sendDelete(Mockito.anyString(), Mockito.any(IJSON.class),
                Mockito.anyMapOf(String.class, String.class))).thenThrow(new BadResponseException("error"));

        Map<String, String> headers = Collections.singletonMap("userId", "1");
        thrown.expect(BadResponseException.class);
        httpRequestSender.sendDelete("/recipes/1", null, headers);
    }
}