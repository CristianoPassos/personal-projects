package de.cristiano.driver.service.driver;

import de.cristiano.driver.domainobject.DriverDO;
import de.cristiano.driver.domainvalue.OnlineStatus;
import de.cristiano.driver.exception.ConstraintsViolationException;
import de.cristiano.driver.exception.EntityNotFoundException;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.springframework.data.domain.Page;

public interface DriverService
{

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    DriverDO find(Long driverId) throws EntityNotFoundException;

    List<DriverDO> find(OnlineStatus onlineStatus);

    Page<DriverDO> findAllByExample(@Nonnull DriverDO driverDO, int pageNumber, int pageSize);

    void updateActiveCar(long driverId, @Nullable String carUuid);

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;
}
