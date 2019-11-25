package de.cristiano.driver.controller.mapper.car;

import de.cristiano.driver.controller.mapper.DriverMapper;
import de.cristiano.driver.datatransferobject.DriverSearchResponseDTO;
import de.cristiano.driver.domainobject.DriverDO;
import javax.annotation.Nonnull;

import org.springframework.data.domain.Page;

import static de.cristiano.driver.controller.mapper.ListMapper.makeDTOList;

public interface DriverSearchMapper
{

    static DriverSearchResponseDTO makeDTO(@Nonnull Page<DriverDO> page)
    {
        return DriverSearchResponseDTO.builder()
            .drivers(makeDTOList(page, DriverMapper::makeDTO))
            .page(page.getNumber())
            .totalPages(page.getTotalPages())
            .totalElements(page.getTotalElements())
            .build();
    }

}
