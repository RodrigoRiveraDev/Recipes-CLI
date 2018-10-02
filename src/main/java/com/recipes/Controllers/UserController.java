package com.recipes.Controllers;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import com.recipes.DTO.User;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private List<User> userList = new ArrayList<User>();

    @RequestMapping(method = RequestMethod.POST)
    public User registerUser(@RequestBody User newUser) {
        userList.add(newUser);
        return newUser;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> userList() {
        return this.userList;
    }
}
