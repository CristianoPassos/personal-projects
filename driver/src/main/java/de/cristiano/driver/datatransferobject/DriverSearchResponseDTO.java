package de.cristiano.driver.datatransferobject;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@ApiModel
@AllArgsConstructor(access = PRIVATE)
public class DriverSearchResponseDTO
{
    int page;

    int totalPages;

    long totalElements;

    Iterable<DriverDTO> drivers;
}
