package de.cristiano.driver.dataaccessobject.car;

import de.cristiano.driver.domainobject.car.CarDO;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface CarRepository extends CrudRepository<CarDO, Long>, QueryByExampleExecutor<CarDO>
{

    Optional<CarDO> findByDriverId(@Nullable Long driverId);

    Optional<CarDO> findByUuid(@Nonnull String uuid);
}
