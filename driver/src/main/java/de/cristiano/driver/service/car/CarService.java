package de.cristiano.driver.service.car;

import de.cristiano.driver.domainobject.car.CarDO;
import java.util.Optional;
import javax.annotation.Nonnull;
import org.springframework.data.domain.Page;

public interface CarService
{

    CarDO create(@Nonnull CarDO car);

    void delete(@Nonnull String uuid);

    Page<CarDO> findAllByExample(@Nonnull CarDO carDO, int pageNumber, int pageSize);

    Optional<CarDO> findByUuid(@Nonnull String uuid);

    Optional<CarDO> updateByUuid(@Nonnull String uuid, @Nonnull CarDO makeDO);

    CarDO selectCar(@Nonnull String carUuid, @Nonnull Long driverId);

    void deselectCar(@Nonnull Long driverId);
}
