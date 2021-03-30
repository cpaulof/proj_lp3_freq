package app.bd.model;

import javax.persistence.*;

@Entity
public class User implements Base{

    @Id @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private Boolean logged;

    @Column(nullable = true)
    private String token;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isLogged() {
        return this.logged;
    }

    public Boolean getLogged() {
        return this.logged;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}

