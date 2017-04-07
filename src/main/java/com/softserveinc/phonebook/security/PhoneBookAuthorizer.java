
package com.softserveinc.phonebook.security;

import org.springframework.stereotype.Component;

import com.softserveinc.phonebook.api.User;

import io.dropwizard.auth.Authorizer;

@Component
public class PhoneBookAuthorizer implements Authorizer<User> {

    @Override
    public boolean authorize(User user, String role) {
        return role.equals(user.getUserRole());
    }
}