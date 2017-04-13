
package com.softserveinc.phonebook.kafka.consumer;

public class ConsumerException extends RuntimeException {

    public ConsumerException(Exception e) {
        super(e);
    }

    private static final long serialVersionUID = -143160441269426349L;
}
