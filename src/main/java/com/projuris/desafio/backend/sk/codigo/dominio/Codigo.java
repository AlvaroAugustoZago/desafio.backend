package com.projuris.desafio.backend.sk.codigo.dominio;

import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.Serializable;

import com.projuris.desafio.backend.infra.domain.ValueObject;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

public interface Codigo extends Serializable, ValueObject {

    String identificador();

    public static Codigo from(String codigo) {

        if (isBlank(codigo)) {
            return naoInformado();
        }

        return CodigoExterno.from(codigo);
    }

    public static Codigo naoInformado() {
        return CodigoNaoInformado.EMPTY;
    }

    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor(access = PRIVATE)
    static final class CodigoNaoInformado implements Codigo {

        private static final long serialVersionUID = -2342959573460665204L;

        static final Codigo EMPTY = new CodigoNaoInformado();

        @Override
        public String identificador() {
            return null;
        }
    }

}
