package project.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Login implements Comparable<Login> {
    //region ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    //endregion

    //region CONSTRUCTOR

    /**
     *
     * @param username  Username to log in on the online account
     * @param password  Password to log in on the online account
     *
     * @should Create a valid Login with all above details
     * @should Check if the username is unique TODO
     * @should Check if the password has at least 2 characters TODO
     * @should Throw illegal argument exception if the password has less than 2 characters TODO
     */
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
    //endregion

    //region METHODS

    //endregion

    //region Helper Methods

    //endregion

    //region GETTERS & SETTERS
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //endregion

    //region TOSTRING, HASH, EQUALS, COMPARE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return Objects.equals(username, login.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public int compareTo(Login o) {
        if (this.username.equals(o.username)) {
            return 0;
        } else return -1;
    }

    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    //endregion
}

