package recipesCLI.HttpRequestSender;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import recipesCLI.CustomExceptions.BadResponseException;
import recipesCLI.CustomExceptions.CustomConnectionException;
import recipes.sharedDomain.DTO.IngredientDTO;
import recipes.sharedDomain.DTO.RecipeDTO;
import recipes.sharedDomain.DTO.UserDTO;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class HttpRequestSenderTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private ObjectMapper mapper;
    private HttpRequestSender httpRequestSender;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
        httpRequestSender = new HttpRequestSender();
    }

    @Test
    public void sendGet() throws BadResponseException, CustomConnectionException {
        String response = httpRequestSender.sendGet("/users", null, null);
        assertThat(response, CoreMatchers.containsString("["));
        assertThat(response, CoreMatchers.containsString("]"));
    }

    @Test
    public void sendPost() throws BadResponseException, CustomConnectionException {
        UserDTO userDTO = new UserDTO("fullName", "email", "password");
        String response = httpRequestSender.sendPost("/users", userDTO, null);
        assertThat(response, CoreMatchers.containsString(userDTO.getFullName()));
        assertThat(response, CoreMatchers.containsString(userDTO.getEmail()));
        assertThat(response, CoreMatchers.containsString(userDTO.getPassword()));
    }

    @Test
    public void sendPostThrownBadResponseException()
            throws BadResponseException, CustomConnectionException {
        UserDTO userDTO = new UserDTO("fullName1", "email1", "password1");
        httpRequestSender.sendPost("/users", userDTO, null);
        thrown.expect(BadResponseException.class);
        httpRequestSender.sendPost("/users", userDTO, null);
    }

    @Test
    public void sendPut() throws CustomConnectionException, BadResponseException {
        UserDTO userDTO = new UserDTO("fullNamePut", "emailPut", "passwordPut");
        Map<String, String> headers = new HashMap<>();
        headers.put("userId", "1");
        httpRequestSender.sendPost("/users", userDTO, headers);
        userDTO.setFullName("NewFullName");
        String response = httpRequestSender.sendPut("/users/1", userDTO, headers);
        assertThat(response, CoreMatchers.containsString(userDTO.getFullName()));
    }

    @Test
    public void sendPutBadResponseException() throws CustomConnectionException, BadResponseException {
        UserDTO userDTO = new UserDTO("fullNamePut2", "emailPut2", "passwordPut2");
        Map<String, String> headers = new HashMap<>();
        headers.put("userId", "2");
        httpRequestSender.sendPost("/users", userDTO, headers);
        userDTO.setFullName("NewFullName");
        thrown.expect(BadResponseException.class);
        httpRequestSender.sendPut("/users/1", userDTO, headers);
    }

    @Test
    public void sendDelete() throws BadResponseException, CustomConnectionException, IOException {
        UserDTO userDTO = new UserDTO("fullNameDelete", "emailDelete", "passwordDelete");
        Map<String, String> headers = new HashMap<>();
        headers.put("userId", "1");
        httpRequestSender.sendPost("/users", userDTO, headers);
        List<IngredientDTO> ingredientDTOList = Collections.singletonList(
                new IngredientDTO("name", 14, "gr"));
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setIngredients(ingredientDTOList);
        recipeDTO.setHowElaborate("recipe");
        String recipeJson = httpRequestSender.sendPost("/recipes", recipeDTO, headers);
        RecipeDTO recipe = mapper.readerFor(RecipeDTO.class).readValue(recipeJson);
        String response = httpRequestSender.sendDelete("/recipes/"+recipe.getId(), null, headers);
        assertThat(response, CoreMatchers.containsString("Successfully deleted"));
    }

    @Test
    public void sendDeleteBadResponseException() throws BadResponseException, CustomConnectionException {
        Map<String, String> headers = new HashMap<>();
        headers.put("userId", "1");
        thrown.expect(BadResponseException.class);
        httpRequestSender.sendDelete("/recipes/1", null, headers);
    }
}