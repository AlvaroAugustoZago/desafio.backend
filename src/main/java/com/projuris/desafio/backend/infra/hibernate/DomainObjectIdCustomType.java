package com.projuris.desafio.backend.infra.hibernate;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.id.ResultSetIdentifierConsumer;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;
import org.springframework.lang.NonNull;

import com.projuris.desafio.backend.infra.domain.DomainObjectId;

/**
 * Tipo customizado de {@link DomainObjectId}s, e respectivos subtipos, para o Hibernate.
 * Isso permite utilizar {@link DomainObjectId}s como chave primárias; para isso, é preciso, criar uma subclasse para cada
 * subtipo de {@link DomainObjectId}.
 *
 * @author Alvaro Augusto Zago
 *
 * @param <K> tipo do identificador.
 * @see DomainObjectIdTypeDescriptor
 */
public abstract class DomainObjectIdCustomType<K extends DomainObjectId> extends AbstractSingleColumnStandardBasicType<K>
    implements ResultSetIdentifierConsumer {

    private static final long serialVersionUID = 4882242389741003990L;

    /**
     * Cria um novo {@code DomainObjectIdCustomType}.
     * Na subclasse é preciso fornecer um construtor padrão e invocar este construtor.
     *
     * @param domainObjectIdTypeDescriptor o {@link DomainObjectIdTypeDescriptor} para este tipo de identificador.
     */
    public DomainObjectIdCustomType(@NonNull DomainObjectIdTypeDescriptor<K> domainObjectIdTypeDescriptor) {
        super(VarcharTypeDescriptor.INSTANCE, domainObjectIdTypeDescriptor);
    }

    @Override
    public Serializable consumeIdentifier(ResultSet resultSet) {
        try {
            return from(resultSet.getString(1));
        } catch (SQLException ex) {
            throw new IllegalStateException("Não foi possível extrair, do ResultSet, o identificador.", ex);
        }
    }

    @Override
    public String getName() {
        return getJavaTypeDescriptor().getJavaType().getSimpleName();
    }

    protected K from(String value) {
        return getJavaTypeDescriptor().fromString(value);
    }
}
