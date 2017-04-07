
package com.softserveinc.phonebook.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.softserveinc.phonebook.api.User;
import com.softserveinc.phonebook.service.UserService;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

@Component
public class PhoneBookAuthenticator implements Authenticator<BasicCredentials, User> {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        User user = userService.getUserByName(credentials.getUsername());

        if (user != null && "ACTIVE".equals(user.getUserStatus())
                && encoder.matches(credentials.getPassword(), user.getPassword())) {
            return Optional.of(user);
        }
        return Optional.empty();
    }
}