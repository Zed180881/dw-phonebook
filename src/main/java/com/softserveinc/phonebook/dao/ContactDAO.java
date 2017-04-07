
package com.softserveinc.phonebook.dao;

import java.util.List;

import com.softserveinc.phonebook.api.Contact;

public interface ContactDAO {

    Contact getContactById(int id);

    int createContact(Contact contact);

    void updateContact(Contact contact);

    void deleteContact(int id);

    List<Contact> getAllContacts();

    boolean hasNoDuplicates(Contact contact);

    List<Contact> getByFullName(String firstName, String lastName);

}
