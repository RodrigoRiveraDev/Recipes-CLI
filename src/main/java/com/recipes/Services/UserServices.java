package com.recipes.Services;

import com.recipes.DTO.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices implements IUserServices {
    private List<User> userList;

    public UserServices() {
        userList = new ArrayList<>();
    }

    @Override
    public void save(User user) {
        userList.add(user);
    }

    @Override
    public List<User> getUserList() {
        return userList;
    }
}
