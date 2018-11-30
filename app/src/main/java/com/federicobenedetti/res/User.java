package com.federicobenedetti.res;

public class User {

    private String name;
    private String email;
    private String picURL;
    private String token;

    public User() {
    }

    public User(String name, String email, String picURL, String token) {
        this.name = name;
        this.email = email;
        this.picURL = picURL;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPicURL() {
        return picURL;
    }

    public String getToken() {
        return this.token;
    }
}
