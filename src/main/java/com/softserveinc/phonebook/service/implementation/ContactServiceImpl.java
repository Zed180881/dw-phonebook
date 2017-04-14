
package com.softserveinc.phonebook.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.phonebook.api.Contact;
import com.softserveinc.phonebook.dao.AuditEventType;
import com.softserveinc.phonebook.dao.ContactDAO;
import com.softserveinc.phonebook.service.ContactAuditEventService;
import com.softserveinc.phonebook.service.ContactService;

@Service("contactService")
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactDAO contactDAO;

    @Autowired
    private ContactAuditEventService contactAuditEventService;

    @Override
    @Transactional(readOnly = true)
    public Contact getContactById(int id) {
        return contactDAO.getContactById(id);
    }

    @Override
    public int createContact(String username, Contact contact) {
        int newContactID = contactDAO.createContact(contact);
        contact.setId(newContactID);
        contactAuditEventService.createContactAuditEvent(username, contact, AuditEventType.CREATE);
        return newContactID;
    }

    @Override
    public void updateContact(String username, Contact contact) {
        contactAuditEventService.createContactAuditEvent(username, contact, AuditEventType.UPDATE);
        contactDAO.updateContact(contact);
    }

    @Override
    public void deleteContact(String username, int id) {
        contactAuditEventService.createContactAuditEvent(username, getContactById(id), AuditEventType.DELETE);
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
