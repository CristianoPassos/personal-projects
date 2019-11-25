package de.cristiano.driver.controller.mapper.car;

import de.cristiano.driver.datatransferobject.CarDTO;
import de.cristiano.driver.domainobject.car.CarDO;

import javax.annotation.Nonnull;

public interface CarMapper
{
    static CarDO makeDO(@Nonnull CarDTO carDTO)
    {
        return CarDO.builder()
            .convertible(carDTO.getConvertible())
            .licensePlate(carDTO.getLicensePlate())
            .rating(carDTO.getRating())
            .seatCount(carDTO.getSeatCount())
            .engineTypeId(carDTO.getEngineTypeId())
            .manufacturerId(carDTO.getManufacturerId())
            .driverId(carDTO.getDriverId())
            .build();
    }

    static CarDTO makeDTO(@Nonnull CarDO carDO)
    {
        return CarDTO.builder()
            .convertible(carDO.getConvertible())
            .engineTypeId(carDO.getEngineTypeId())
            .licensePlate(carDO.getLicensePlate())
            .manufacturerId(carDO.getManufacturerId())
            .rating(carDO.getRating())
            .seatCount(carDO.getSeatCount())
            .uuid(carDO.getUuid())
            .driverId(carDO.getDriverId())
            .build();
    }


}
