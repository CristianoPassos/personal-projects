package de.cristiano.farm.util;

import de.cristiano.farm.api.dto.CoordinateDTO;
import de.cristiano.farm.api.dto.FieldDTO;
import de.cristiano.farm.domain.Coordinate;
import de.cristiano.farm.domain.Field;
import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@UtilityClass
public class FieldConverter {

    public static FieldDTO convertTo(@Nonnull final Field field) {
        return FieldDTO.builder()
                .name(field.getName())
                .build();
    }

    public static Collection<FieldDTO> convertTo(@Nonnull final Collection<Field> fields) {
        return fields.stream().map(FieldConverter::convertTo).collect(toSet());
    }

    public static Field convertFrom(@Nonnull final FieldDTO field) {
        Field entity = new Field();

        entity.setName(field.getName());

        return entity;
    }

}
