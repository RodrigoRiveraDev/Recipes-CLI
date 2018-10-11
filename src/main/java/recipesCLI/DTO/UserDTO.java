package recipesCLI.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDTO implements IJSON {

    private long id;
    private String fullName;
    private String email;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public UserDTO(long id, String fullName, String email, String password) {
        this.id = id;
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
