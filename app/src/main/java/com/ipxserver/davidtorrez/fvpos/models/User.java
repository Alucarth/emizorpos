package com.ipxserver.davidtorrez.fvpos.models;

import java.io.Serializable;

/**
 * Created by David-Pc on 07/06/2015.
 */
public class User implements Serializable {
    //for request
    private String user,password;
    //for response
    private String username,nitempresa;

    public User(){}
    public User(String user,String nitempresa,String password)
    {
        this.user= user;
        this.nitempresa=nitempresa;
        this.password=password;

    }
    public void setUser(String user) {
        this.user = user;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNitempresa(String nitempresa) {
        this.nitempresa = nitempresa;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user+"@"+nitempresa;
    }

    public String getUsername() {
        return username;

    }

    public String getNitempresa() {
        return nitempresa;
    }

    public String getPassword() {
        return password;
    }
}
