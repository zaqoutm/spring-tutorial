package com.zaqout.spring_tutorial.security;

public class AppUser {

    private Long id;
    private String username;
    private String password;
    private String oauth;
    private String email;
    private String pictureUrl;
    private String roles;

    public String getUsername() {
        return username;
    }

    public AppUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getRoles() {
        return roles;
    }

    public AppUser setRoles(String roles) {
        this.roles = roles;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AppUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AppUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getOauth() {
        return oauth;
    }

    public AppUser setOauth(String oauth) {
        this.oauth = oauth;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AppUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public AppUser setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", oauth='" + oauth + '\'' +
                ", email='" + email + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }
}
