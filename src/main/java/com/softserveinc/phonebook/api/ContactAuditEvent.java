
package com.softserveinc.phonebook.api;

public class ContactAuditEvent {

    private int id;

    private int contactId;

    private String eventDate;

    private String changeType;

    private String originValue;

    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getOriginValue() {
        return originValue;
    }

    public void setOriginValue(String originValue) {
        this.originValue = originValue;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static class ContactAuditEventBuilder {

        private int contactId;

        private String changeType;

        private String originValue;

        private String userName;

        public ContactAuditEventBuilder setContactId(int contactId) {
            this.contactId = contactId;
            return this;
        }

        public ContactAuditEventBuilder setChangeType(String changeType) {
            this.changeType = changeType;
            return this;
        }

        public ContactAuditEventBuilder setOriginValue(String originValue) {
            this.originValue = originValue;
            return this;
        }

        public ContactAuditEventBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public ContactAuditEvent build() {
            ContactAuditEvent contactAuditEvent = new ContactAuditEvent();
            contactAuditEvent.contactId = this.contactId;
            contactAuditEvent.changeType = this.changeType;
            contactAuditEvent.originValue = this.originValue;
            contactAuditEvent.userName = this.userName;
            return contactAuditEvent;
        }

    }
}
