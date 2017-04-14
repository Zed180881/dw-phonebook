
package com.softserveinc.phonebook.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.phonebook.api.Contact;
import com.softserveinc.phonebook.api.ContactAuditEvent;
import com.softserveinc.phonebook.dao.AuditEventType;
import com.softserveinc.phonebook.dao.ContactAuditEventDAO;
import com.softserveinc.phonebook.exceptions.AuditEventServiceException;
import com.softserveinc.phonebook.service.ContactAuditEventService;

@Service("contactAuditEventService")
public class ContactAuditEventServiceImpl implements ContactAuditEventService {

    @Autowired
    private ContactAuditEventDAO contactAuditEventDAO;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public int createContactAuditEvent(String username, Contact contact, AuditEventType auditEventType) {
        ContactAuditEvent contactAuditEvent;
        try {
            contactAuditEvent = new ContactAuditEvent.ContactAuditEventBuilder().setContactId(contact.getId())
                    .setChangeType(auditEventType.toString()).setOriginValue(mapper.writeValueAsString(contact))
                    .setUserName(username).build();
        } catch (JsonProcessingException e) {
            throw new AuditEventServiceException(e);
        }
        return contactAuditEventDAO.createContactAuditEvent(contactAuditEvent);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactAuditEvent> getAllContactAuditEvents() {
        return contactAuditEventDAO.getAllContactAuditEvents();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactAuditEvent> getContactAuditEventsByUsername(String username) {
        return contactAuditEventDAO.getContactAuditEventsByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactAuditEvent> getContactAuditEventsByContactId(int contactId) {
        return contactAuditEventDAO.getContactAuditEventsByContactId(contactId);
    }

}
