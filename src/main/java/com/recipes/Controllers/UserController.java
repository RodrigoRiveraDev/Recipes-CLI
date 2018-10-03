package com.recipes.Controllers;

import java.util.List;
import com.recipes.DTO.User;
import com.recipes.Exceptions.ResourceNotFoundException;
import com.recipes.Exceptions.UnauthorizedException;
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

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity registerUser(@RequestBody User newUser) {
        try {
            userServices.save(newUser);
            return new ResponseEntity<>(newUser.toString(), HttpStatus.CREATED);
        } catch (IllegalArgumentException illegalArgumentException) {
            return new ResponseEntity<>(illegalArgumentException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity getUserById(@PathVariable long id) {
        try {
            User foundedUser = userServices.findUserbyId(id);
            return new ResponseEntity<>(foundedUser, HttpStatus.OK);
        } catch (IllegalArgumentException ilegalArugmentException) {
            return new ResponseEntity<>(ilegalArugmentException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(resourceNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public HttpEntity updateUser(@RequestHeader(value="userId") int userId, @PathVariable long id, @RequestBody User dataToUpdate) {
        try {
            User foundedUser = userServices.updateUserInfo(id, dataToUpdate, userId);
            return new ResponseEntity<>(foundedUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException ilegalArugmentException) {
            return new ResponseEntity<>(ilegalArugmentException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(resourceNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        } catch (UnauthorizedException unauthorizedException) {
            return new ResponseEntity<>(unauthorizedException.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<User> userList() {
        return userServices.getUserList();
    }
}
