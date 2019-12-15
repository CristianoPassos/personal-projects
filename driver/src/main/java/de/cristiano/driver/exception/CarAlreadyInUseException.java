package de.cristiano.driver.exception;

import javax.annotation.Nonnull;

import static java.lang.String.format;

public class CarAlreadyInUseException extends RuntimeException
{
    public CarAlreadyInUseException(@Nonnull String message, @Nonnull Object... params)
    {
        super(format(message, params));
    }
}
