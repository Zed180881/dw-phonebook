
package com.softserveinc.phonebook.service;

import java.util.List;

import com.softserveinc.phonebook.api.Contact;

public interface ContactService {

    Contact getContactById(int id);

    int createContact(Contact contact);

    void updateContact(Contact contact);

    void deleteContact(int id);

    List<Contact> getAllContacts();

    List<Contact> getByFullName(String firstName, String lastName);

}
