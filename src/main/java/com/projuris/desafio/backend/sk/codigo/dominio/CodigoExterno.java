package com.projuris.desafio.backend.sk.codigo.dominio;

import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PRIVATE;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = PRIVATE)
public final class CodigoExterno implements Codigo {

    private static final long serialVersionUID = -693810920276671258L;

    @NotBlank
    @Size(min = 1, max = 50)
    private String identificador;

    public static CodigoExterno from(@NonNull String codigo) {
        return new CodigoExterno(requireNonNull(codigo));
    }
}
