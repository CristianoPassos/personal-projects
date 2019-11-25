package de.cristiano.driver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.Nonnull;

public interface JsonUtils
{
    ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static String toJson(@Nonnull Object object)
    {
        try
        {
            return OBJECT_MAPPER.writeValueAsString(object);
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }
    }

    static String toJson(@Nonnull Object[] objects)
    {
        try
        {
            return OBJECT_MAPPER.writeValueAsString(objects);
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }
    }
}
