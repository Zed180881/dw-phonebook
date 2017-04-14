
package com.softserveinc.phonebook.exceptions;

public class AuditEventServiceException extends RuntimeException {

    private static final long serialVersionUID = 4763628376595793041L;

    public AuditEventServiceException(Exception e) {
        super(e);
    }

}
