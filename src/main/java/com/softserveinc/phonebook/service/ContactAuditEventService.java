
package com.softserveinc.phonebook.service;

import java.util.List;

import com.softserveinc.phonebook.api.Contact;
import com.softserveinc.phonebook.api.ContactAuditEvent;
import com.softserveinc.phonebook.dao.AuditEventType;

public interface ContactAuditEventService {

    int createContactAuditEvent(String username, Contact contact, AuditEventType auditEventType);

    List<ContactAuditEvent> getAllContactAuditEvents();

    List<ContactAuditEvent> getContactAuditEventsByUsername(String username);

    List<ContactAuditEvent> getContactAuditEventsByContactId(int contactId);

}
