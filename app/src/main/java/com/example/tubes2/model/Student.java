package com.example.tubes2.model;

public class Student extends User{
    final String npm;
    final String initial_year;

    public Student(String email, String password, String role, String npm, String initial_year) {
        super(email, password, role);
        this.npm = npm;
        this.initial_year = initial_year;
    }

    public Student() {
        super(new String(),new String(),new String());
        this.initial_year = new String();
        this.npm = new String();
    }

    public String getNpm (){return this.npm;}
    public String getInitialYear (){return this.initial_year;}
}
