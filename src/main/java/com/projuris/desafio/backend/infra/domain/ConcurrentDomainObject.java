package com.projuris.desafio.backend.infra.domain;

import org.springframework.lang.Nullable;

/**
 * Interface para objetos de domínio que usam bloqueio otimista para impedir que várias sessões simultâneas atualizem o objeto
 * ao mesmo tempo.
 * 
 * @author Thiago A. de Souza Weber
 *
 */
public interface ConcurrentDomainObject extends DomainObject {

    /**
     * Retorna o número da versão do bloqueio otimista.
     *
     * @return o número da versão do bloqueio ou {@code null} caso ainda não possua.
     */
    @Nullable
    Long lockVersion();

}
