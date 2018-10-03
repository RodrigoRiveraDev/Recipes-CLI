package com.recipes.Controllers;

import java.util.List;
import com.recipes.DTO.Recipe;
import com.recipes.Exceptions.ResourceNotFoundException;
import com.recipes.Services.IRecipeServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private IRecipeServices recipeServices;

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity registerRecipe(@RequestBody Recipe newRecipe) {
        try {
            recipeServices.save(newRecipe);
            return new ResponseEntity<>(newRecipe, HttpStatus.OK);
        } catch (IllegalArgumentException illegalArgumentException) {
            return new ResponseEntity<>(illegalArgumentException.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.PUT)
    public HttpEntity updateRecipe(@PathVariable long id, @RequestBody Recipe dataToUpdate) {
        try {
            Recipe updatedRecipe = recipeServices.updateRecipeInfo(id, dataToUpdate);
            return new ResponseEntity<>(updatedRecipe, HttpStatus.CREATED);
        } catch (IllegalArgumentException illegalArgumentException) {
            return new ResponseEntity<>(illegalArgumentException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(resourceNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Recipe> recipeList() {
        return recipeServices.getRecipeList();
    }
}
