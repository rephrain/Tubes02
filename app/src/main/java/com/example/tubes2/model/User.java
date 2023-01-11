package com.example.tubes2.model;

public class User {
    private String token;
    private String email;
    private String password;
    private String role;
    private String name;
    private String id;

    public User (String email, String password, String role){
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public String getToken(){return this.token;}
    public String getEmail(){return this.email;}
    public String getPassword(){return this.password;}
    public String getRole(){return this.role;}
    public String getName(){return this.name;}
    public String getId() {
        return id;
    }
    public void setToken(String token){this.token = token;}
    public void setName(String name){this.name = name;}
    public void setId(String id){this.id = id;}

}
