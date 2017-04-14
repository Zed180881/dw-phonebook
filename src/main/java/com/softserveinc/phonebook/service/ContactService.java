
package com.softserveinc.phonebook.service;

import java.util.List;

import com.softserveinc.phonebook.api.Contact;

public interface ContactService {

    Contact getContactById(int id);

    int createContact(String username, Contact contact);

    void updateContact(String username, Contact contact);

    void deleteContact(String username, int id);

    List<Contact> getAllContacts();

    List<Contact> getByFullName(String firstName, String lastName);

}
