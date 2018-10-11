package recipesCLI.DTO;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSetter;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserDTO implements IJSON {

    @JsonProperty
    private long id;
    @JsonProperty
    private String fullName;
    @JsonProperty
    private String email;
    @JsonProperty
    private String password;

    public UserDTO() {
    }

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
    public long getId() {
        return id;
    }

    /**
     * @param id The value param that will replace the actual id value
     */
    @JsonSetter
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return It will return the current fullName value
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName The value param that will replace the actual fullName value
     */
    @JsonSetter
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return It will return the current email value
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The value param that will replace the actual email value
     */
    @JsonSetter
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return It will return the current password value
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password The value param that will replace the password id value
     */
    @JsonSetter
    public void setPassword(String password) {
            this.password = password;
        }

    /**
     * @return It will return the object as a String with Json format
     */
    @Override
    public String toString() {
        return "{"+
                "\"id\" : \"" + id + "\"" +
                ",\"fullName\" : \"" + fullName + "\"" +
                ",\"email\" : \"" + email + "\"" +
                ",\"password\" : \"" + password + "\""
                +"}";
    }

    /**
     * @return It will return the object as a String with Json format
     */
    @Override
    public String toJSON() {
            return this.toString();
        }
}
