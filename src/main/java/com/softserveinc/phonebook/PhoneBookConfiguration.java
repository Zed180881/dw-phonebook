
package com.softserveinc.phonebook;

import java.util.Properties;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class PhoneBookConfiguration extends Configuration {

    @NotEmpty
    private String kafkaTopic;

    @NotNull
    private Boolean testKafka;

    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();

    @JsonDeserialize(using = PropertiesDeserializer.class)
    private Properties producerProperties = new Properties();

    @JsonDeserialize(using = PropertiesDeserializer.class)
    private Properties consumerProperties = new Properties();

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    public Properties getProducerProperties() {
        return producerProperties;
    }

    public Properties getConsumerProperties() {
        return consumerProperties;
    }

    public String getKafkaTopic() {
        return kafkaTopic;
    }

    public Boolean isTestKafka() {
        return testKafka;
    }
}
