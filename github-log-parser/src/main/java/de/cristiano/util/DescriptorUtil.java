package de.cristiano.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import de.cristiano.domain.Descriptor;
import lombok.extern.slf4j.Slf4j;
import util.ResourceUtils;

import javax.annotation.Nonnull;
import java.util.Optional;

@Slf4j
public class DescriptorUtil {

    private static final YAMLFactory FACTORY = new YAMLFactory()
            .configure(YAMLGenerator.Feature.MINIMIZE_QUOTES, true)
            .configure(YAMLGenerator.Feature.WRITE_DOC_START_MARKER, false)
            .configure(YAMLGenerator.Feature.INDENT_ARRAYS, true);

    private static final String REPLACE_TAG = "${body}";

    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(FACTORY);

    public static Optional<String> toYAML(@Nonnull final Descriptor descriptor) {
        try {
            return Optional.of(ResourceUtils.readText("templates/descriptor.yaml.template")
                    .replace(REPLACE_TAG, YAML_MAPPER.writeValueAsString(descriptor)));
        } catch (JsonProcessingException ex) {
            log.error("Error generating descriptor:", ex);
        }

        return Optional.empty();
    }
}
