
package com.softserveinc.phonebook.kafka.producer;

import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.softserveinc.phonebook.PhoneBookConfiguration;
import com.softserveinc.phonebook.api.Contact;

@Component
public class ContactProducer implements Runnable {

    private KafkaProducer<String, byte[]> kafkaProducer;

    private PhoneBookConfiguration configuration;

    @Autowired
    public ContactProducer(PhoneBookConfiguration configuration) {
        this.configuration = configuration;
        this.kafkaProducer = new KafkaProducer<>(configuration.getProducerProperties());
    }

    @Override
    public void run() {
        while (true) {
            Contact contact = new Contact();
            contact.setFirstName(LocalDate.now().toString());
            contact.setLastName(LocalTime.now().toString());
            contact.setPhone("097-0000000");
            try {
                kafkaProducer.send(new ProducerRecord<String, byte[]>(configuration.getKafkaTopic(),
                        SerializationUtils.serialize(contact)));
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new ProducerException(e);
            }

        }

    }

}
