package com.projuris.desafio.backend.infra.util;

import static com.projuris.desafio.backend.infra.util.UUIDUtils.checkUUIDArgument;
import static com.projuris.desafio.backend.infra.util.UUIDUtils.isUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Utilit\u00E1rio de UUID")
public class UUIDUtilsTest {

    @Test
    @DisplayName("V\u00E1lido")
    void uuidValido() {
        assertTrue(isUUID("66e47b82-4ce6-4352-ad09-a3ff0a2f11e4"));
    }

    @Test
    @DisplayName("Inv\u00E1lido")
    void uuidInvalido() {
        assertFalse(isUUID("66e47b8204ce6043520ad090a3ff0a2f11e4"));
    }

    @Test
    @DisplayName("4º dash inv\u00E1lido")
    void uuidComDash4Invalido() {
        assertFalse(isUUID("66e47b82-4ce6-4352aad09aa3ff0a2f11e4"));
    }

    @Test
    @DisplayName("5º dash inv\u00E1lido")
    void uuidComDash5Invalido() {
        assertFalse(isUUID("66e47b82-4ce6-4352-ad09-a3ff0-2f11e4"));
    }

    @Test
    @DisplayName("Inv\u00E1lido com 40 d\u00EDgitos")
    void uuidCom40Digitos() {
        assertFalse(isUUID("66e47b82-4ce6-4352-ad09-a3ff0a2f11e40000"));
    }

    @Test
    @DisplayName("Verifica\u00E7\u00E3o v\u00E1lida")
    void checkUuidArgumentValido() {
        String uuid = "66e47b82-4ce6-4352-ad09-a3ff0a2f11e4";
        assertEquals(checkUUIDArgument(uuid), uuid);
    }

    @Test
    @DisplayName("Verifica\u00E7\u00E3o inv\u00E1lida")
    void checkUuidArgumentInvalido() {
        assertThrows(IllegalArgumentException.class,
            () -> checkUUIDArgument("66e47b8204ce6043520ad090a3ff0a2f11e4"));
    }
}