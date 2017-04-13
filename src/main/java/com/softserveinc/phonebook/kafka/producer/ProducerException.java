
package com.softserveinc.phonebook.kafka.producer;

public class ProducerException extends RuntimeException {

    public ProducerException(Exception e) {
        super(e);
    }

    private static final long serialVersionUID = -143160441269426349L;
}
