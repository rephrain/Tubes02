package com.example.tubes2.model;

public class User {
    private String token;
    private String email;
    private String password;
    private String role;

    public User (String email, String password, String role){
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public String getToken(){return this.token;}
    public String getEmail(){return this.email;}
    public String getPassword(){return this.password;}
    public String getRole(){return this.role;}
    public void setToken(String token){this.token = token;}
}
