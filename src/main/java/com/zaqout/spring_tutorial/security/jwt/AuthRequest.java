package com.zaqout.spring_tutorial.security.jwt;

public class AuthRequest {
    private String username;
    private Object password;

    public String getUsername() {
        return this.username;
    }

    public Object getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
