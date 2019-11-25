package de.cristiano.driver.exception;

import javax.annotation.Nonnull;

import static java.lang.String.format;

//I always want to rollback the transaction when it happens.
public class ConstraintsViolationException extends RuntimeException
{

    static final long serialVersionUID = -3387516993224229948L;


    public ConstraintsViolationException(@Nonnull String message)
    {
        super(message);
    }


    public ConstraintsViolationException(@Nonnull String message, @Nonnull Object... params)
    {
        super(format(message, params));
    }

}
