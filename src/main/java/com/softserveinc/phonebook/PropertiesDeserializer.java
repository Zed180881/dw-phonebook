
package com.softserveinc.phonebook;

import java.io.IOException;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class PropertiesDeserializer extends JsonDeserializer<Properties> {

    @Override
    public Properties deserialize(JsonParser jp, DeserializationContext context) throws IOException {
        Properties props = new Properties();
        if (!jp.hasCurrentToken() || jp.getCurrentToken() != JsonToken.START_OBJECT)
            return props;

        String name = null;
        for (JsonToken token = jp.nextToken(); token != JsonToken.END_OBJECT; token = jp.nextToken()) {
            switch (token) {
            case FIELD_NAME:
                name = jp.getCurrentName();
                break;
            case VALUE_STRING:
                String value = jp.getValueAsString();
                props.setProperty(name, value);
                break;
            default:
                throw new UnsupportedOperationException(
                        "Unsupported configuration item " + token + ". Only string values are supported");
            }
        }

        return props;
    }
}