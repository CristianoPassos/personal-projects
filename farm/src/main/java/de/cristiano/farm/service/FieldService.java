package de.cristiano.farm.service;

import de.cristiano.farm.api.dto.FieldDTO;
import de.cristiano.farm.api.exception.NotFoundException;
import de.cristiano.farm.domain.Field;
import de.cristiano.farm.repository.FieldRepository;
import de.cristiano.farm.util.FieldConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.Collection;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Service
@RequiredArgsConstructor
public class FieldService {

    private final FieldRepository repository;

    public FieldDTO getField(@Nonnull final Long id) {
        return repository.findById(id)
                .map(FieldConverter::convertTo)
                .orElseThrow(NotFoundException::new);
    }

    public Collection<FieldDTO> searchFields(@Nonnull final FieldDTO example) {
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", contains());
        Example<Field> searchCriteria = Example.of(FieldConverter.convertFrom(example), matcher);

        return FieldConverter.convertTo(repository.findAll(searchCriteria));
    }
}
