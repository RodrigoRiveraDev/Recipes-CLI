package com.recipes.Controllers;

import java.util.List;
import com.recipes.DTO.User;
import com.recipes.Services.IUserServices;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserServices userServices;

    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    public HttpEntity<String> registerUser(@RequestBody User newUser) {
        userServices.save(newUser);
        return new HttpEntity<String>(newUser.toString());
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> userList() {
        return userServices.getUserList();
    }
}
