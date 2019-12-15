package de.cristiano.driver.controller.mapper.car;

import de.cristiano.driver.datatransferobject.CarSearchResponseDTO;
import de.cristiano.driver.domainobject.car.CarDO;
import javax.annotation.Nonnull;

import org.springframework.data.domain.Page;

import static de.cristiano.driver.controller.mapper.ListMapper.makeDTOList;

public interface CarSearchMapper
{

    static CarSearchResponseDTO makeDTO(@Nonnull Page<CarDO> page)
    {
        return CarSearchResponseDTO.builder()
            .cars(makeDTOList(page, CarMapper::makeDTO))
            .page(page.getNumber())
            .totalPages(page.getTotalPages())
            .totalElements(page.getTotalElements())
            .build();
    }

}
