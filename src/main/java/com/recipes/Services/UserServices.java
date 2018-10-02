package com.recipes.Services;

import com.recipes.DTO.User;
import com.recipes.Exceptions.ResourceNotFoundException;
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

    @Override
    public User findUserbyId(long id) {
        User foundedUser = null;
        int index = userList.size();
        if(id < 0) {
            throw new IllegalArgumentException("Negative id is not valid");
        }

        while(foundedUser == null && --index >= 0) {
            foundedUser = (userList.get(index).hasId(id)) ? userList.get(index) : null;
        }

        if(foundedUser == null) {
            throw new ResourceNotFoundException(User.class, id);
        }

        return foundedUser;
    }

    public User updateUserInfo(long id, User dataToUpdate) {
        User foundedUser = null;
        int index = userList.size();
        if(id < 0) {
            throw new IllegalArgumentException("Negative id is not valid");
        }

        while(foundedUser == null && --index >= 0) {
            foundedUser = (userList.get(index).hasId(id)) ? userList.get(index) : null;
        }

        if(foundedUser == null) {
            throw new ResourceNotFoundException(User.class, id);
        }

        foundedUser.updateInfo(dataToUpdate);
        return foundedUser;
    }
}
