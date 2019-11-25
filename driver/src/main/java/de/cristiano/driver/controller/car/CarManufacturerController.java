package de.cristiano.driver.controller.car;

import de.cristiano.driver.controller.mapper.car.CarManufacturerMapper;
import de.cristiano.driver.datatransferobject.CarManufacturerDTO;
import de.cristiano.driver.service.car.CarManufacturerService;
import java.util.Collection;
import java.util.stream.StreamSupport;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.toSet;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

//It really is a sub resource in the context of a ride hailing application.
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/car/manufacturer")
public class CarManufacturerController
{

    private final CarManufacturerService service;


    // Almost static and rarely updated entity, we can just add a cache latter hence no need for pagination.
    @GetMapping(path = "/all", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CarManufacturerDTO>> getAll()
    {
        return ResponseEntity.ok(StreamSupport.stream(service.findAll().spliterator(), true)
            .map(CarManufacturerMapper::makeDTO)
            .collect(toSet()));
    }

}
