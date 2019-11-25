package de.cristiano.driver.dataaccessobject.car;

import de.cristiano.driver.domainobject.car.CarManufacturerDO;
import org.springframework.data.repository.CrudRepository;

public interface CarManufacturerRepository extends CrudRepository<CarManufacturerDO, Long>
{

}
