package com.projuris.desafio.backend.sk.codigo.repositorio;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.projuris.desafio.backend.sk.codigo.dominio.Codigo;

@Converter(autoApply = true)
public class CodigoConverter implements AttributeConverter<Codigo, String> {

    @Override
    public String convertToDatabaseColumn(Codigo codigo) {
        return codigo.identificador();
    }

    @Override
    public Codigo convertToEntityAttribute(String dbData) {
        return Codigo.from(dbData);
    }

}
