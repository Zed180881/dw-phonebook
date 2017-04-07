
package com.softserveinc.phonebook.service;

import java.util.List;

import com.softserveinc.phonebook.api.User;

public interface UserService {

    User getUserByName(String username);

    User getUserById(int id);

    int createUser(User user);

    void updateUser(User user);

    void deleteUser(int id);

    List<User> getAllUsers();

    boolean hasNoDuplicates(User user);
}