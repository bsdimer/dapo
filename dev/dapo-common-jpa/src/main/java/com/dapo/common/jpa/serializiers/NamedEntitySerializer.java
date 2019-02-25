package com.dapo.common.jpa.serializiers;

import com.dapo.common.jpa.model.NamedEntity;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by dimomass on 09.02.19.
 */
public class NamedEntitySerializer extends JsonSerializer<NamedEntity> {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void serialize(NamedEntity entity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", entity.getId().toString());
        jsonGenerator.writeStringField("name", entity.getName());
        jsonGenerator.writeEndObject();
    }
}
