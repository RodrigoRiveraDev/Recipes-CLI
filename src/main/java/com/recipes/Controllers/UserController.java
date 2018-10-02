package com.recipes.Controllers;

import java.util.List;
import com.recipes.DTO.User;
import com.recipes.Exceptions.ResourceNotFoundException;
import com.recipes.Services.IUserServices;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserServices userServices;

    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    public HttpEntity<String> registerUser(@RequestBody User newUser) throws IllegalArgumentException {
        if(!newUser.hasAllParameters()) {
            throw new IllegalArgumentException("All the parameters must not be nulls or empties");
        }
        userServices.save(newUser);
        return new HttpEntity<String>(newUser.toString());
    }

    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.GET)
    public HttpEntity<User> getUserById(@PathVariable long id) {
        try {
            User foundedUser = userServices.findUserbyId(id);
            return new ResponseEntity<>(foundedUser, HttpStatus.OK);
        } catch (IllegalArgumentException ilegalArugmentException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<User> userList() {
        return userServices.getUserList();
    }
}
