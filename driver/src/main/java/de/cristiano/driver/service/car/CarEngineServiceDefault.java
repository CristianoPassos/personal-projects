package de.cristiano.driver.service.car;

import de.cristiano.driver.dataaccessobject.car.CarEngineRepository;
import de.cristiano.driver.domainobject.car.CarEngineDO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarEngineServiceDefault implements CarEngineService
{

    private final CarEngineRepository repository;


    @Override
    public Iterable<CarEngineDO> findAll()
    {
        return repository.findAll();
    }


}
