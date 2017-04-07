
package com.softserveinc.phonebook.api;

import java.security.Principal;

public class User implements Principal {

    private int id;

    private String name;

    private String password;

    private String userRole;

    private String userStatus;

    public User() {
    }

    public User(int id, String name, String password, String userRole, String userStatus) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

}
