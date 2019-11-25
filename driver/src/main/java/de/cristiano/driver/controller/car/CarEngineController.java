package de.cristiano.driver.controller.car;

import de.cristiano.driver.controller.mapper.car.CarEngineMapper;
import de.cristiano.driver.datatransferobject.CarEngineDTO;
import de.cristiano.driver.service.car.CarEngineService;
import java.util.Collection;
import java.util.stream.StreamSupport;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.toSet;

//Same observations as #CarManufacturerControler
@RestController
@RequestMapping("v1/car/engine")
@RequiredArgsConstructor
public class CarEngineController
{
    private final CarEngineService service;


    //Same observations as #CarManufacturerControler
    @GetMapping(value = "/all")
    public ResponseEntity<Collection<CarEngineDTO>> getAll()
    {
        return ResponseEntity.ok(StreamSupport.stream(service.findAll().spliterator(), true)
            .map(CarEngineMapper::makeDTO)
            .collect(toSet()));
    }

}
