
package com.softserveinc.phonebook.kafka.consumer;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.softserveinc.phonebook.PhoneBookConfiguration;
import com.softserveinc.phonebook.api.Contact;
import com.softserveinc.phonebook.exceptions.ConsumerException;
import com.softserveinc.phonebook.service.ContactService;

@Component
public class ContactConsumer implements Runnable {

    private static final String USERNAME = "BadRobot";

    private KafkaConsumer<String, byte[]> kafkaConsumer;

    private PhoneBookConfiguration configuration;

    private ContactService contactService;

    @Autowired
    public ContactConsumer(PhoneBookConfiguration configuration, ContactService contactService) {
        this.configuration = configuration;
        this.contactService = contactService;
        this.kafkaConsumer = new KafkaConsumer<>(configuration.getConsumerProperties());
    }

    @Override
    public void run() {
        kafkaConsumer.subscribe(Arrays.asList(configuration.getKafkaTopic()));
        while (true) {
            ConsumerRecords<String, byte[]> records = kafkaConsumer.poll(1000);
            for (ConsumerRecord<String, byte[]> record : records) {
                try {
                    Contact contact = (Contact) SerializationUtils.deserialize(record.value());
                    contactService.createContact(USERNAME, contact);
                } catch (Exception e) {
                    throw new ConsumerException(e);
                }
            }

        }

    }

}
