package foo.guilherme.webnovel.model;

import java.util.Date;
import java.util.UUID;

public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private String name;

    public User(String email, String username, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = String.valueOf(UUID.randomUUID());
    }

    public User() {
        this.id = String.valueOf(UUID.randomUUID());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
