package de.cristiano.farm.api;

import de.cristiano.farm.api.dto.FieldDTO;
import de.cristiano.farm.service.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequiredArgsConstructor
@RequestMapping({"", "v1"})
public class FieldController {

    private final FieldService fieldService;

    @RequestMapping(method = POST,
            value = "/field/search",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public Collection<FieldDTO> searchField(@RequestBody @Valid final FieldDTO query) {
        return fieldService.searchFields(query);
    }

    @RequestMapping(method = GET,
            value = "/field/{id}",
            produces = APPLICATION_JSON_VALUE)
    public FieldDTO searchField(@PathVariable final Long id) {
        return fieldService.getField(id);
    }
}
