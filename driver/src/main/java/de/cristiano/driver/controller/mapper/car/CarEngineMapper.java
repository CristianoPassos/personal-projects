package de.cristiano.driver.controller.mapper.car;

import de.cristiano.driver.datatransferobject.CarEngineDTO;
import de.cristiano.driver.domainobject.car.CarEngineDO;

import javax.annotation.Nonnull;

public interface CarEngineMapper
{

    static CarEngineDTO makeDTO(@Nonnull CarEngineDO carEngineDO)
    {
        return CarEngineDTO.builder()
            .id(carEngineDO.getId())
            .name(carEngineDO.getName())
            .build();
    }
}
