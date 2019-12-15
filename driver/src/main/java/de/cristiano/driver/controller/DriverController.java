package de.cristiano.driver.controller;

import de.cristiano.driver.controller.mapper.DriverMapper;
import de.cristiano.driver.datatransferobject.DriverDTO;
import de.cristiano.driver.datatransferobject.DriverSearchRequestDTO;
import de.cristiano.driver.datatransferobject.DriverSearchResponseDTO;
import de.cristiano.driver.domainobject.DriverDO;
import de.cristiano.driver.domainvalue.OnlineStatus;
import de.cristiano.driver.exception.ConstraintsViolationException;
import de.cristiano.driver.exception.EntityNotFoundException;
import de.cristiano.driver.service.driver.DriverService;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static de.cristiano.driver.controller.mapper.DriverMapper.makeDO;
import static de.cristiano.driver.controller.mapper.DriverMapper.makeDTO;
import static de.cristiano.driver.controller.mapper.ListMapper.makeDTOList;
import static de.cristiano.driver.controller.mapper.car.DriverSearchMapper.makeDTO;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController
{

    private final DriverService driverService;


    @Autowired
    public DriverController(final DriverService driverService)
    {
        this.driverService = driverService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException
    {
        DriverDO driverDO = makeDO(driverDTO);
        return makeDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@PathVariable long driverId) throws EntityNotFoundException
    {
        driverService.delete(driverId);
    }


    @GetMapping
    public Iterable<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus)
    {
        return makeDTOList(driverService.find(onlineStatus), DriverMapper::makeDTO);
    }


    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@PathVariable long driverId) throws EntityNotFoundException
    {
        return makeDTO(driverService.find(driverId));
    }


    //Just to keep simple, I will filter by values provided.
    @PostMapping(value = "/search")
    @ApiOperation("Search drivers with provided values")
    public ResponseEntity<DriverSearchResponseDTO> search(@Nonnull @Valid @RequestBody DriverSearchRequestDTO searchRequest)
    {
        final Page<DriverDO> pageSearchResult = driverService.findAllByExample(makeDO(searchRequest.getDriver()), searchRequest.getPage(), searchRequest.getPageSize());

        return ResponseEntity.ok(makeDTO(pageSearchResult));
    }


    //We should have a proper Patch endpoint, fully allowing partial updates.
    //Possible solutions are JSON Merge Patch or JSON Patch
    //As Spring does not support it out of the box, creating the required infrastructure would require time which I must use to finish the all tasks
    @PatchMapping("/{driverId}")
    @ApiOperation("Update driver active car")
    public void updateActiveCar(@PathVariable long driverId, @Nullable @RequestParam String carUuid)
    {
        driverService.updateActiveCar(driverId, carUuid);
    }


    @PutMapping("/{driverId}")
    public void updateLocation(
        @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }
}
