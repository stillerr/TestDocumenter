package com.testdoc.example.businesslogic;

public class User {

    public String email;
    public String password;


    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static User unknown() {
        return new User(null, null);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
