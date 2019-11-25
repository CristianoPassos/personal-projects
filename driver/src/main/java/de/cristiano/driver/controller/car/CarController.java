package de.cristiano.driver.controller.car;

import de.cristiano.driver.controller.mapper.car.CarMapper;
import de.cristiano.driver.datatransferobject.CarDTO;
import de.cristiano.driver.datatransferobject.CarSearchRequestDTO;
import de.cristiano.driver.datatransferobject.CarSearchResponseDTO;
import de.cristiano.driver.domainobject.car.CarDO;
import de.cristiano.driver.exception.EntityNotFoundException;
import de.cristiano.driver.service.car.CarService;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Nonnull;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static de.cristiano.driver.controller.mapper.car.CarMapper.makeDO;
import static de.cristiano.driver.controller.mapper.car.CarMapper.makeDTO;
import static de.cristiano.driver.controller.mapper.car.CarSearchMapper.makeDTO;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/car")
@RequiredArgsConstructor
public class CarController
{

    private final CarService service;


    @PostMapping
    public ResponseEntity<CarDTO> create(@Valid @RequestBody CarDTO carDTO)
    {
        CarDO carDO = service.create(makeDO(carDTO));

        return ResponseEntity.status(CREATED)
            .body(makeDTO(carDO));
    }


    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable String uuid)
    {
        service.delete(uuid);
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/{uuid}")
    public ResponseEntity<CarDTO> get(@PathVariable String uuid)
    {
        return service.findByUuid(uuid)
            .map(CarMapper::makeDTO)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new EntityNotFoundException("Could not find Car: " + uuid));
    }


    //Just to keep simple, I will filter by values provided.
    @PostMapping(value = "/search")
    @ApiOperation("Search cars with provided values.")
    public ResponseEntity<CarSearchResponseDTO> search(@Nonnull @Valid @RequestBody CarSearchRequestDTO searchRequest)
    {
        final Page<CarDO> pageSearchResult = service.findAllByExample(makeDO(searchRequest.getCar()), searchRequest.getPage(), searchRequest.getPageSize());

        return ResponseEntity.ok(makeDTO(pageSearchResult));
    }


    //So much to do, no patching, whole entity update :)
    @PutMapping("/{uuid}")
    public ResponseEntity<CarDTO> update(@PathVariable String uuid, @Nonnull @Valid @RequestBody CarDTO carDTO)
    {
        return service.updateByUuid(uuid, makeDO(carDTO))
            .map(CarMapper::makeDTO)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new EntityNotFoundException("Could not find Car: " + uuid));
    }

}
