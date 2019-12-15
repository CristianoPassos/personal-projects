package de.cristiano.driver.service.car;

import de.cristiano.driver.domainobject.car.CarManufacturerDO;

public interface CarManufacturerService
{
    Iterable<CarManufacturerDO> findAll();
}
