
package com.softserveinc.phonebook.dao;

import java.util.List;

import com.softserveinc.phonebook.api.ContactAuditEvent;

public interface ContactAuditEventDAO {

    int createContactAuditEvent(ContactAuditEvent contactAuditEvent);

    List<ContactAuditEvent> getAllContactAuditEvents();

    List<ContactAuditEvent> getContactAuditEventsByUsername(String username);

    List<ContactAuditEvent> getContactAuditEventsByContactId(int contactId);

}
