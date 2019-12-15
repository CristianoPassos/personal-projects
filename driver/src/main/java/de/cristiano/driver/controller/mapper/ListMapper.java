package de.cristiano.driver.controller.mapper;

import java.util.function.Function;
import java.util.stream.StreamSupport;
import javax.annotation.Nonnull;

import static java.util.stream.Collectors.toList;

public interface ListMapper
{
    static <T, R> Iterable<R> makeDTOList(@Nonnull Iterable<T> domainObjects, @Nonnull Function<T, R> mapper)
    {
        return StreamSupport.stream(domainObjects.spliterator(), true)
            .map(mapper)
            .collect(toList());
    }
}
