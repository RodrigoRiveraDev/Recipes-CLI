package recipesCLI.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import recipes.sharedDomain.DTO.IngredientDTO;
import recipes.sharedDomain.DTO.RecipeDTO;

import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;


public class RecipeServicesCLITest {

    private RecipeServicesCLI recipeServicesCLI;
    private ObjectMapper mapper;
    private RecipeDTO dummyRecipe;

    @Before
    public void setUp() {
        IngredientDTO ingredient = new IngredientDTO("sugar", 150, "gr");
        mapper = new ObjectMapper();
        recipeServicesCLI = mock(RecipeServicesCLI.class);
        dummyRecipe = new RecipeDTO();
        dummyRecipe.setHowElaborate("recipe");
        dummyRecipe.setIngredients(Collections.singletonList(ingredient));
    }

    @Test
    public void finishRegisterRecipe() throws IOException {
        Mockito.when(recipeServicesCLI.finishRegisterRecipe(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(dummyRecipe.toJSON());

        String response = recipeServicesCLI.finishRegisterRecipe("recipe", 1);
        RecipeDTO recipeDTO = mapper.readerFor(RecipeDTO.class).readValue(response);
        assertThat(recipeDTO.getHowElaborate(), CoreMatchers.equalTo("recipe"));
    }

    @Test
    public void finishRegisterRecipeError() {
        Mockito.when(recipeServicesCLI.finishRegisterRecipe(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn("error");

        String response = recipeServicesCLI.finishRegisterRecipe("recipe", 1000);
        assertThat(response, CoreMatchers.containsString("error"));
    }

    @Test
    public void finishUpdateRecipe() throws IOException {
        RecipeDTO recipe = new RecipeDTO();
        recipe.setIngredients(dummyRecipe.getIngredients());
        recipe.setHowElaborate("newHow");
        Mockito.when(recipeServicesCLI.finishUpdateRecipe(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyInt())).thenReturn(recipe.toJSON());

        String response = recipeServicesCLI.finishUpdateRecipe("newHow", "1", 1);
        recipe = mapper.readerFor(RecipeDTO.class).readValue(response);
        assertThat(recipe.getHowElaborate(), CoreMatchers.equalTo("newHow"));
    }

    @Test
    public void finishUpdateRecipeError() throws IOException {
        Mockito.when(recipeServicesCLI.finishUpdateRecipe(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyInt())).thenReturn("error");

        String response = recipeServicesCLI.finishUpdateRecipe("newHow", "1200", 2);
        assertThat(response, CoreMatchers.containsString("error"));
    }

    @Test
    public void getAllRecipes() {
        Mockito.when(recipeServicesCLI.getAllRecipes()).thenReturn("[]");

        String response = recipeServicesCLI.getAllRecipes();
        assertThat(response, CoreMatchers.containsString("["));
        assertThat(response, CoreMatchers.containsString("]"));
    }

    @Test
    public void getRecipeByIdError() {
        Mockito.when(recipeServicesCLI.getRecipeById(Mockito.anyString()))
                .thenReturn("error");

        String response = recipeServicesCLI.getRecipeById("100000");
        assertThat(response, CoreMatchers.containsString("error"));
    }

    @Test
    public void getRecipeById() throws IOException {
        Mockito.when(recipeServicesCLI.getRecipeById(Mockito.anyString()))
                .thenReturn(dummyRecipe.toJSON());

        String response = recipeServicesCLI.getRecipeById("1");
        RecipeDTO recipeDTO2 = mapper.readerFor(RecipeDTO.class).readValue(response);
        assertThat(dummyRecipe.getId(), CoreMatchers.is(recipeDTO2.getId()));
        assertThat(dummyRecipe.getHowElaborate(), CoreMatchers.is(recipeDTO2.getHowElaborate()));
    }

    @Test
    public void deleteRecipe() throws IOException {
        Mockito.when(recipeServicesCLI.deleteRecipe(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn("Recipe with id: 1 successfully deleted");

        String response = recipeServicesCLI.deleteRecipe("1", 1);
        assertThat(response, CoreMatchers.containsString("successfully"));
    }

    @Test
    public void deleteRecipeError() {
        Mockito.when(recipeServicesCLI.deleteRecipe(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn("error");

        String response = recipeServicesCLI.deleteRecipe("10000", 1);
        assertThat(response, CoreMatchers.containsString("error"));
    }
}