package de.cristiano.driver.service.car;

import de.cristiano.driver.config.CarConfig;
import de.cristiano.driver.dataaccessobject.car.CarRepository;
import de.cristiano.driver.domainobject.car.CarDO;
import de.cristiano.driver.exception.CarAlreadyInUseException;
import de.cristiano.driver.exception.ConstraintsViolationException;
import de.cristiano.driver.exception.EntityNotFoundException;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang.RandomStringUtils.randomAlphanumeric;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceDefault implements CarService
{

    private final CarRepository repository;

    private final CarConfig config;


    private void checkCarAvailability(@Nonnull CarDO carDO)
    {
        if (carDO.getDriverId() != null)
        {
            log.info("Car: {} is already selected", carDO.getUuid());

            throw new CarAlreadyInUseException("Car: %s already selected", carDO.getUuid());
        }
    }


    @Override
    @Transactional
    public CarDO create(@Nonnull CarDO carDO)
    {

        //Better to handle than throw an exception for the case when value is already provided.
        carDO.setUuid(randomAlphanumeric(8).toUpperCase());
        carDO.setDeleted(false);

        validateCar(carDO);

        try
        {
            log.info("Creating new Car: {}", carDO);

            //I am knowingly not going to handle rare uuid collision.
            return repository.save(carDO);
        }
        catch (DataIntegrityViolationException e)
        {
            log.warn("ConstraintsViolationException while creating a car: " + carDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
    }


    @Override
    @Transactional
    public void delete(@Nonnull String uuid)
    {
        log.info("Deleting Car: {}", uuid);

        final CarDO car = findByUuid(uuid)
            .orElseThrow(() -> new EntityNotFoundException("Could not find Car: " + uuid));

        car.setDeleted(true);
    }


    @Override
    public Page<CarDO> findAllByExample(@Nonnull CarDO carDO, int pageNumber, int pageSize)
    {
        final PageRequest page = PageRequest.of(pageNumber, pageSize, ASC, "id");

        final Example<CarDO> example = Example.of(carDO, ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase());

        return repository.findAll(example, page);
    }


    @Override
    public Optional<CarDO> findByUuid(@Nonnull String uuid)
    {
        return repository.findByUuid(uuid);
    }


    @Override
    @Transactional
    public Optional<CarDO> updateByUuid(@Nonnull String uuid, @Nonnull CarDO carDO)
    {
        validateCar(carDO);

        return repository.findByUuid(uuid)
            .map((toUpdate) -> {

                log.info("Updating Car: {}", carDO);

                toUpdate.setConvertible(carDO.getConvertible());
                toUpdate.setEngineTypeId(carDO.getEngineTypeId());
                toUpdate.setLicensePlate(carDO.getLicensePlate());
                toUpdate.setManufacturerId(carDO.getManufacturerId());
                toUpdate.setRating(carDO.getRating());
                toUpdate.setSeatCount(carDO.getSeatCount());

                return toUpdate;
            });
    }


    @Override
    @Transactional
    public CarDO selectCar(@Nonnull String carUuid, @Nonnull Long driverId)
    {
        final CarDO carDO = repository.findByUuid(carUuid)
            .orElseThrow(() -> new EntityNotFoundException("Could not locate Car: " + carUuid));

        log.info("Selecting Car: {} for Driver: {}", carDO.getUuid(), driverId);

        checkCarAvailability(carDO);

        carDO.setDriverId(driverId);

        return carDO;
    }


    @Override
    @Transactional
    public void deselectCar(@Nonnull Long driverId)
    {
        final CarDO carDO = repository.findByDriverId(driverId)
            .orElseThrow(() -> new EntityNotFoundException("Could not locate Car associated with Driver: " + driverId));

        log.info("Deselecting Driver: {} from Car: {}", driverId, carDO.getUuid());

        carDO.setDriverId(null);
    }


    private void validateCar(@Nonnull CarDO carDO)
    {
        if (!config.getValidSeatCounts().contains(carDO.getSeatCount()))
        {
            throw new ConstraintsViolationException("Invalid seat counts, please use one of: %s", config.getValidSeatCounts());
        }
    }
}
