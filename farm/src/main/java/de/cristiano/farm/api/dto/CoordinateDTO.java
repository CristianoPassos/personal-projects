package de.cristiano.farm.api.dto;

import de.cristiano.farm.domain.Field;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
public class CoordinateDTO {

    private Long lon;

    private Long lat;

}
