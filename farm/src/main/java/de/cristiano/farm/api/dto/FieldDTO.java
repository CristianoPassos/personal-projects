package de.cristiano.farm.api.dto;


import de.cristiano.farm.domain.Coordinate;
import lombok.Builder;
import lombok.Data;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Set;

@Data
@Builder
public class FieldDTO {

    @Nullable
    private String name;

    @Nullable
    private Collection<CoordinateDTO> coordinates;
}
