package recipesCLI.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "fullName",
        "password",
        "email"
})
public class UserDTO implements IJSON {

    @JsonProperty("id")
    private long id;
    @JsonProperty("fullName")
    private String fullName;
    @JsonProperty("password")
    private String password;
    @JsonProperty("email")
    private String email;

    public UserDTO() {}

    public UserDTO(@JsonProperty("fullName") String fullName,
                   @JsonProperty("email") String email,
                   @JsonProperty("password") String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    /**
     * @return It will return the current id value
     */
    @JsonProperty("id")
    public long getId() {
        return id;
    }

    /**
     * @param id The value param that will replace the actual id value
     */
    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return It will return the current fullName value
     */
    @JsonProperty("fullName")
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName The value param that will replace the actual fullName value
     */
    @JsonProperty("fullName")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return It will return the current password value
     */
    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    /**
     * @param password The value param that will replace the actual password value
     */
    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return It will return the current email value
     */
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    /**
     * @param email The value param that will replace the actual email value
     */
    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return It will return the object as a String with Json format
     */
    @Override
    public String toJSON() {
        return "{"+
                "\"id\" : \"" + id + "\"" +
                ",\"fullName\" : \"" + fullName + "\"" +
                ",\"email\" : \"" + email + "\"" +
                ",\"password\" : \"" + password + "\""
                +"}";
    }
}