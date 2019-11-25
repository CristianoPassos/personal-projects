package de.cristiano.driver.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@JsonInclude(NON_NULL)
@AllArgsConstructor(access = PRIVATE)
public class CarManufacturerDTO
{
    private long id;

    private String name;

}
