
package com.softserveinc.phonebook.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.phonebook.api.Contact;
import com.softserveinc.phonebook.dao.ContactDAO;
import com.softserveinc.phonebook.service.ContactService;

@Service("contactService")
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactDAO contactDAO;

    @Override
    @Transactional(readOnly = true)
    public Contact getContactById(int id) {
        return contactDAO.getContactById(id);
    }

    @Override
    public int createContact(Contact contact) {
        return contactDAO.createContact(contact);
    }

    @Override
    public void updateContact(Contact contact) {
        contactDAO.updateContact(contact);
    }

    @Override
    public void deleteContact(int id) {
        contactDAO.deleteContact(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> getAllContacts() {
        return contactDAO.getAllContacts();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> getByFullName(String firstName, String lastName) {
        return contactDAO.getByFullName(firstName, lastName);
    }

}
