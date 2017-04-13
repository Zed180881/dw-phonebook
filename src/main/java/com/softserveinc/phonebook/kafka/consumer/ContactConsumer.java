
package com.softserveinc.phonebook.kafka.consumer;

import java.io.IOException;
import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.phonebook.PhoneBookConfiguration;
import com.softserveinc.phonebook.api.Contact;
import com.softserveinc.phonebook.service.ContactService;

@Component
public class ContactConsumer implements Runnable {

    private KafkaConsumer<String, String> kafkaConsumer;

    private PhoneBookConfiguration configuration;

    private ObjectMapper mapper;

    private ContactService contactService;

    @Autowired
    public ContactConsumer(PhoneBookConfiguration configuration, ContactService contactService) {
        this.configuration = configuration;
        this.contactService = contactService;
        this.kafkaConsumer = new KafkaConsumer<>(configuration.getConsumerProperties());
        this.mapper = new ObjectMapper();
    }

    @Override
    public void run() {
        kafkaConsumer.subscribe(Arrays.asList(configuration.getKafkaTopic()));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                Contact contact;
                try {
                    contact = mapper.readValue(record.value(), Contact.class);
                    contactService.createContact(contact);
                } catch (IOException e) {
                    throw new ConsumerException(e);
                }
            }

        }

    }

}
