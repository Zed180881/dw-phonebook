
package com.softserveinc.phonebook.exceptions;

public class ProducerException extends RuntimeException {

    private static final long serialVersionUID = -143160441269426349L;

    public ProducerException(Exception e) {
        super(e);
    }
}
