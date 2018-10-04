package Utilitaries;

import DTO.UserDTO;

public class UserFactory {

    public static UserDTO createUser(String fullName, String email, String password) {
        return new UserDTO(0, fullName, email, password);
    }

}
