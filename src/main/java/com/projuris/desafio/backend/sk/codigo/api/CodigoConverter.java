package com.projuris.desafio.backend.sk.codigo.api;

import static lombok.AccessLevel.PRIVATE;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.projuris.desafio.backend.sk.codigo.dominio.Codigo;

import lombok.NoArgsConstructor;

@JsonComponent
@NoArgsConstructor(access = PRIVATE)
public class CodigoConverter {

    public static class CodigoSerializer extends JsonSerializer<Codigo> {
        @Override
        public void serialize(Codigo codigo, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(codigo.identificador());
        }
    }

    public static class CodigoDeserializer extends JsonDeserializer<Codigo> {
        @Override
        public Codigo deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return Codigo.from(ctxt.readValue(p, String.class));
        }
    }

}
