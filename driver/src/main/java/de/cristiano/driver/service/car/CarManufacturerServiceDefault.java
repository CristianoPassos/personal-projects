package de.cristiano.driver.service.car;

import de.cristiano.driver.dataaccessobject.car.CarManufacturerRepository;
import de.cristiano.driver.domainobject.car.CarManufacturerDO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarManufacturerServiceDefault implements CarManufacturerService
{

    private final CarManufacturerRepository repository;


    @Override
    public Iterable<CarManufacturerDO> findAll()
    {
        return repository.findAll();
    }
}
