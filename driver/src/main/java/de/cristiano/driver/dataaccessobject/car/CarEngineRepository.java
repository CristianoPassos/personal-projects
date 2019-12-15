package de.cristiano.driver.dataaccessobject.car;

import de.cristiano.driver.domainobject.car.CarEngineDO;
import org.springframework.data.repository.CrudRepository;

public interface CarEngineRepository extends CrudRepository<CarEngineDO, Long>
{

}
