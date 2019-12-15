package de.cristiano.driver.controller.mapper.car;

import de.cristiano.driver.datatransferobject.CarManufacturerDTO;
import de.cristiano.driver.domainobject.car.CarManufacturerDO;

import javax.annotation.Nonnull;

public interface CarManufacturerMapper
{

    static CarManufacturerDTO makeDTO(@Nonnull CarManufacturerDO carManufacturerDO)
    {
        return CarManufacturerDTO.builder()
            .id(carManufacturerDO.getId())
            .name(carManufacturerDO.getName())
            .build();
    }
}
