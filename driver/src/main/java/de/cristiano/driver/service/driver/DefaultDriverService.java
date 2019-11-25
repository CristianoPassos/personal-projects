package de.cristiano.driver.service.driver;

import de.cristiano.driver.dataaccessobject.DriverRepository;
import de.cristiano.driver.domainobject.DriverDO;
import de.cristiano.driver.domainvalue.GeoCoordinate;
import de.cristiano.driver.domainvalue.OnlineStatus;
import de.cristiano.driver.exception.ConstraintsViolationException;
import de.cristiano.driver.exception.EntityNotFoundException;
import de.cristiano.driver.service.car.CarService;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.springframework.data.domain.Sort.Direction.ASC;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultDriverService implements DriverService
{

    private final DriverRepository repository;

    private final CarService carService;


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException
    {
        DriverDO driver;
        try
        {
            driver = repository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            log.warn("ConstraintsViolationException while creating a driver:" + driverDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus)
    {
        return repository.findByOnlineStatus(onlineStatus);
    }


    @Override
    public Page<DriverDO> findAllByExample(@Nonnull DriverDO driverDO, int pageNumber, int pageSize)
    {
        final PageRequest page = PageRequest.of(pageNumber, pageSize, ASC, "id");

        final Example<DriverDO> example = Example.of(
            driverDO,
            ExampleMatcher.matchingAny()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING, true))
                .withIgnoreNullValues()
                .withIgnoreCase()
        );

        return repository.findAll(example, page);
    }


    @Override
    @Transactional
    public void updateActiveCar(long driverId, @Nullable String carUuid)
    {
        final DriverDO driverDO = findDriverChecked(driverId);

        performDriverActiveCarValidations(driverDO);

        if (isBlank(carUuid))
        {
            carService.deselectCar(driverDO.getId());
        }
        else
        {
            carService.selectCar(carUuid, driverDO.getId());
        }
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        return repository.findById(driverId)
            .orElseThrow(() -> new EntityNotFoundException("Could not locate Driver: " + driverId));
    }


    //Perform all business validation required.
    //Throws ConstraintsViolationException if any fail.
    private void performDriverActiveCarValidations(@Nonnull DriverDO driverDO)
    {
    }

}
