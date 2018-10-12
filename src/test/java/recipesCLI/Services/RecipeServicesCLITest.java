package recipesCLI.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import recipesCLI.DTO.RecipeDTO;
import recipesCLI.HttpRequestSender.HttpRequestSender;

import java.io.IOException;

import static org.junit.Assert.assertThat;


public class RecipeServicesCLITest {

    private RecipeServicesCLI recipeServicesCLI;
    private ObjectMapper mapper;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
        recipeServicesCLI = new RecipeServicesCLI(new HttpRequestSender());
    }

    @Test
    public void finishRegisterRecipe() throws IOException {
        recipeServicesCLI.startRegisteringRecipe();
        recipeServicesCLI.addIngredient("sugar", "100", "gr");
        String response = recipeServicesCLI.finishRegisterRecipe("recipe", 1);
        RecipeDTO recipeDTO = mapper.readerFor(RecipeDTO.class).readValue(response);
        assertThat(recipeDTO.getHowElaborate(), CoreMatchers.equalTo("recipe"));
    }

    @Test
    public void finishRegisterRecipeError() throws IOException {
        recipeServicesCLI.startRegisteringRecipe();
        String response = recipeServicesCLI.finishRegisterRecipe("recipe", 1000);
        assertThat(response, CoreMatchers.containsString("error"));
    }

    @Test
    public void finishUpdateRecipe() throws IOException {
        recipeServicesCLI.startRegisteringRecipe();
        recipeServicesCLI.addIngredient("sugar", "100", "gr");
        String response = recipeServicesCLI.finishRegisterRecipe("recipe", 1);
        RecipeDTO recipeDTO = mapper.readerFor(RecipeDTO.class).readValue(response);
        recipeServicesCLI.startRegisteringRecipe();
        String recipeId = Long.toString(recipeDTO.getId());
        response = recipeServicesCLI.finishUpdateRecipe("newHow", recipeId, 1);
        recipeDTO = mapper.readerFor(RecipeDTO.class).readValue(response);
        assertThat(recipeDTO.getHowElaborate(), CoreMatchers.equalTo("newHow"));
    }

    @Test
    public void finishUpdateRecipeError() throws IOException {
        recipeServicesCLI.startRegisteringRecipe();
        recipeServicesCLI.addIngredient("sugar", "100", "gr");
        String response = recipeServicesCLI.finishRegisterRecipe("recipe", 1);
        RecipeDTO recipeDTO = mapper.readerFor(RecipeDTO.class).readValue(response);
        recipeServicesCLI.startRegisteringRecipe();
        String recipeId = Long.toString(recipeDTO.getId());
        response = recipeServicesCLI.finishUpdateRecipe("newHow", recipeId, 2);
        assertThat(response, CoreMatchers.containsString("error"));
    }

    @Test
    public void getAllRecipes() {
        String response = recipeServicesCLI.getAllRecipes();
        assertThat(response, CoreMatchers.containsString("["));
        assertThat(response, CoreMatchers.containsString("]"));
    }

    @Test
    public void getRecipeByIdError() {
        String response = recipeServicesCLI.getRecipeById("100000");
        assertThat(response, CoreMatchers.containsString("error"));
    }

    @Test
    public void getRecipeById() throws IOException {
        recipeServicesCLI.startRegisteringRecipe();
        recipeServicesCLI.addIngredient("sugar", "100", "gr");
        String response = recipeServicesCLI.finishRegisterRecipe("recipe", 1);
        RecipeDTO recipeDTO = mapper.readerFor(RecipeDTO.class).readValue(response);
        String recipeId = Long.toString(recipeDTO.getId());
        response = recipeServicesCLI.getRecipeById(recipeId);
        RecipeDTO recipeDTO2 = mapper.readerFor(RecipeDTO.class).readValue(response);
        assertThat(recipeDTO.getId(), CoreMatchers.is(recipeDTO2.getId()));
        assertThat(recipeDTO.getHowElaborate(), CoreMatchers.is(recipeDTO2.getHowElaborate()));
    }

    @Test
    public void deleteRecipe() throws IOException {
        recipeServicesCLI.startRegisteringRecipe();
        recipeServicesCLI.addIngredient("sugar", "100", "gr");
        String response = recipeServicesCLI.finishRegisterRecipe("recipe", 1);
        RecipeDTO recipeDTO = mapper.readerFor(RecipeDTO.class).readValue(response);
        String recipeId = Long.toString(recipeDTO.getId());
        response = recipeServicesCLI.deleteRecipe(recipeId, 1);
        assertThat(response, CoreMatchers.containsString("successfully"));
    }

    @Test
    public void deleteRecipeError() {
        String response = recipeServicesCLI.deleteRecipe("10000", 1);
        assertThat(response, CoreMatchers.containsString("error"));
    }
}