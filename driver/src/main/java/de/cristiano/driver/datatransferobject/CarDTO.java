package de.cristiano.driver.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@JsonInclude(NON_NULL)
@AllArgsConstructor(access = PRIVATE)
public class CarDTO
{
    @Nullable
    private Boolean convertible;

    @Nullable
    private Long driverId;

    @Nullable
    private Long engineTypeId;

    @Nullable
    private String licensePlate;

    @Nullable
    private Long manufacturerId;

    @Nullable
    private Short rating;

    @Nullable
    private Integer seatCount;

    @Nullable
    private String uuid;

}
