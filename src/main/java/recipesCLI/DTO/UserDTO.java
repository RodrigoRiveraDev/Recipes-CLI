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

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "{"+
                    "\"id\" : \"" + id + "\"" +
                    ",\"fullName\" : \"" + fullName + "\"" +
                    ",\"email\" : \"" + email + "\"" +
                    ",\"password\" : \"" + password + "\""
                    +"}";
        }

        @Override
        public String toJSON() {
            return this.toString();
        }
}
