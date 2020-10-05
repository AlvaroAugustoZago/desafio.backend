package com.projuris.desafio.backend.infra.util;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

import java.util.UUID;

import lombok.experimental.UtilityClass;

/**
 * Utility class to help validate {@link UUID}s values.
 * 
 * @author Alvaro Augusto Zago
 *
 */
@UtilityClass
public final class UUIDUtils {

    /**
     * Check whether the given {@code String} contains actual {@link UUID}.
     * 
     * @param uuid the {@code String} to check (may be {@code null})
     * @return {@code true} if the {@code String} is not {@code null}, its
     *         length is 36, and it is a valid {@link UUID}
     * @throws NullPointerException if {@code uuid} is {@code null}
     * @see UUID#fromString(String)
     */
    public static boolean isUUID(String uuid) {

        requireNonNull(uuid);

        if (uuid.length() > 36) {
            return false;
        }

        int dash1 = uuid.indexOf('-', 0);
        int dash2 = uuid.indexOf('-', dash1 + 1);
        int dash3 = uuid.indexOf('-', dash2 + 1);
        int dash4 = uuid.indexOf('-', dash3 + 1);
        int dash5 = uuid.indexOf('-', dash4 + 1);

        // For any valid input, dash1 through dash4 will be positive and dash5
        // negative, but it's enough to check dash4 and dash5:
        // - if dash1 is -1, dash4 will be -1
        // - if dash1 is positive but dash2 is -1, dash4 will be -1
        // - if dash1 and dash2 is positive, dash3 will be -1, dash4 will be
        //   positive, but so will dash5
        return dash4 >= 0 && dash5 < 0;
    }

    public static String checkUUIDArgument(String uuid) {
        if (!isUUID(uuid))
            throw new IllegalArgumentException(format("The given value (%s) is not a valid UUID.", uuid));
        return uuid;
    }
}
