package usermanagement.models;

import usermanagement.requests.CreateUserRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class UserDetail {

    @Id
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Date lastLogin;

    public UserDetail(String name, String email, String password, Date lastLogIn) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.lastLogin = lastLogIn;
    }

    public UserDetail(CreateUserRequest request) {
        this.name = request.getName();
        this.email = request.getEmail();
        this.password = request.getPassword();
        this.lastLogin = new Date();
    }

    public UserDetail() {}

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
