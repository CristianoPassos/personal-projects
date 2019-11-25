package de.cristiano.driver.controller.mapper;

import de.cristiano.driver.datatransferobject.DriverDTO;
import de.cristiano.driver.domainobject.DriverDO;
import de.cristiano.driver.domainvalue.GeoCoordinate;

public class DriverMapper
{
    public static DriverDO makeDO(DriverDTO driverDTO)
    {
        final DriverDO driverDO = new DriverDO(driverDTO.getUsername(), driverDTO.getPassword());

        return driverDO;
    }


    public static DriverDTO makeDTO(DriverDO driverDO)
    {
        DriverDTO.DriverDTOBuilder driverDTOBuilder = DriverDTO.newBuilder()
            .setId(driverDO.getId())
            .setPassword(driverDO.getPassword())
            .setUsername(driverDO.getUsername());

        GeoCoordinate coordinate = driverDO.getCoordinate();

        if (coordinate != null)
        {
            driverDTOBuilder.setCoordinate(coordinate);
        }

        return driverDTOBuilder.createDriverDTO();
    }

}
