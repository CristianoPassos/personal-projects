package de.cristiano.farm.util;

import de.cristiano.farm.api.dto.CoordinateDTO;
import de.cristiano.farm.domain.Coordinate;
import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@UtilityClass
public class CoordinateConverter {

    public static Collection<CoordinateDTO> convertTo(@Nonnull final Collection<Coordinate> coordinates) {
        return coordinates.stream().map(CoordinateConverter::convertTo).collect(toSet());
    }

    public static Collection<Coordinate> convertFrom(@Nonnull final Collection<CoordinateDTO> coordinates) {
        return coordinates.stream().map(CoordinateConverter::convertFrom).collect(toSet());
    }

    public static CoordinateDTO convertTo(@Nonnull final Coordinate coordinate) {
        return CoordinateDTO.builder()
                .lat(coordinate.getLat())
                .lon(coordinate.getLon())
                .build();
    }

    public static Coordinate convertFrom(@Nonnull final CoordinateDTO coordinate) {
        Coordinate entity = new Coordinate();

        entity.setLat(coordinate.getLat());
        entity.setLon(coordinate.getLon());

        return entity;
    }
}
