package de.cristiano.driver.datatransferobject;

import io.swagger.annotations.ApiModel;
import javax.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@AllArgsConstructor(access = PRIVATE)
@ApiModel("Search car by all attributes, all optional")
public class CarSearchRequestDTO
{

    @Nonnull
    private CarDTO car;

    @Nonnull
    @Builder.Default
    private Integer page = 0;

    @Nonnull
    @Builder.Default
    private Integer pageSize = 10;
}
