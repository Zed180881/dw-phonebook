
package com.softserveinc.phonebook.api;

import java.io.Serializable;

import com.softserveinc.phonebook.validator.Validate;

@Validate
public class Contact implements Serializable {

    private static final long serialVersionUID = -4228903307335783500L;

    private int id;

    private String firstName;

    private String lastName;

    private String phone;

    public Contact() {
    }

    public Contact(int id, String firstName, String lastName, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
