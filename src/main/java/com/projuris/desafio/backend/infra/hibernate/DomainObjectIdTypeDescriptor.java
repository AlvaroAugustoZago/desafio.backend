package com.projuris.desafio.backend.infra.hibernate;

import static java.util.Objects.requireNonNull;

import java.util.function.Function;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.springframework.lang.NonNull;

import com.projuris.desafio.backend.infra.domain.DomainObjectId;

/**
 * Descritor de {@link DomainObjectId}s, e respectivos subtipos, para o Hibernate.
 * 
 * @author Alvaro Augusto Zago
 * 
 * @param <K> tipo do identificador.
 * @see DomainObjectIdCustomType
 */
public class DomainObjectIdTypeDescriptor<K extends DomainObjectId> extends AbstractTypeDescriptor<K> {

    private static final long serialVersionUID = 8625043768156425743L;

    private final transient Function<String, K> factory;

    /**
     * Cria um novo {@code DomainObjectIdTypeDescriptor}.
     *
     * @param type tipo do identificador.
     * @param factory uma fabrica para a criação de novos identificadores.
     */
    public DomainObjectIdTypeDescriptor(@NonNull Class<K> type, @NonNull Function<String, K> factory) {
        super(type);
        this.factory = requireNonNull(factory, "factory must not be null");
    }

    @Override
    public String toString(K value) {
        return value.toUUID();
    }

    @Override
    public K fromString(String string) {
        return factory.apply(string);
    }

    @Override
    public <X> X unwrap(K value, Class<X> type, WrapperOptions options) {

        if (value == null)
            return null;

        if (type.isAssignableFrom(getJavaType()))
            return type.cast(value);

        if (type.isAssignableFrom(String.class))
            return type.cast(toString(value));

        throw unknownUnwrap(type);
    }

    @Override
    public <X> K wrap(X value, WrapperOptions options) {

        if (value == null)
            return null;

        if (getJavaType().isInstance(value))
            return getJavaType().cast(value);

        if (value instanceof String)
            return fromString((String) value);

        throw unknownWrap(value.getClass());
    }
}
