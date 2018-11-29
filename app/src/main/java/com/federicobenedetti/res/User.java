package com.federicobenedetti.res;

public class User {

    private String name;
    private String email;
    private String picURL;

    public User () {
    }

    public User(String name, String email, String picURL) {
        this.name = name;
        this.email = email;
        this.picURL = picURL;
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
}
