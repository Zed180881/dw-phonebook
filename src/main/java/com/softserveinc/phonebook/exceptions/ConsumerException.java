
package com.softserveinc.phonebook.exceptions;

public class ConsumerException extends RuntimeException {

    private static final long serialVersionUID = -143160441269426349L;

    public ConsumerException(Exception e) {
        super(e);
    }
}
