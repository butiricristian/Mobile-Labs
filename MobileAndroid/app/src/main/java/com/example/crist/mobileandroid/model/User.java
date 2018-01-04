package com.example.crist.mobileandroid.model;

/**
 * Created by crist on 02-Jan-18.
 */

public class User {
    private String name, email, password;
    private boolean isPremium;

    public User(String name, String email, String password, boolean isPremium) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isPremium = isPremium;
    }

    public User(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }
}
