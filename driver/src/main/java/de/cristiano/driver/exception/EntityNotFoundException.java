package de.cristiano.driver.exception;

import javax.annotation.Nonnull;

import static java.lang.String.format;

//I always want to rollback the transaction when it happens.
public class EntityNotFoundException extends RuntimeException
{
    static final long serialVersionUID = -3387516993334229948L;


    public EntityNotFoundException(@Nonnull String message, @Nonnull Object... params)
    {
        super(format(message, params));
    }
}
