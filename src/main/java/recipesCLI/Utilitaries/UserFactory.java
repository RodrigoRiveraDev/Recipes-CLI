package recipesCLI.Utilitaries;

import recipes.sharedDomain.DTO.UserDTO;

public class UserFactory {

    /**
     * @param fullName The fullName userDTO
     * @param email The email userDTO
     * @param password The password userDTO
     * @return It will return a UserDTO object with the provided data
     */
    public static UserDTO createUser(String fullName, String email, String password) {
        return new UserDTO(fullName, email, password);
    }
}
